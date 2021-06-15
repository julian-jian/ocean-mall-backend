package com.macro.mall.portal.service.impl;

import java.io.*;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.hutool.core.codec.Base64;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberExample;
import com.macro.mall.portal.domain.UserInfo;
import com.macro.mall.portal.service.UmsMemberService;
import com.macro.mall.portal.service.WxMemberService;
import com.macro.mall.portal.util.WxBase64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@Service
public class WxMemberServiceImpl implements WxMemberService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private UmsMemberService memberService;

    @Value("${wx.APPID}")
    private  static String APPID;

    @Value("${wx.SECRET}")
    private static String SECRET;

   //判断用户登陆前是否注册 未注册就注册 注册就登陆
    public String wxLogin(String code, UserInfo rawData, String signature,
                          String encrypteData, String iv){

//        //System.out.println("用户非敏感信息"+rawData);
//        JSONObject rawDataJson = JSON.parseObject( rawData );
        //System.out.println("签名"+signature);
        JSONObject SessionKeyOpenId = getSessionKeyOrOpenId( code );
        //获取openid和session_key
        String openId = SessionKeyOpenId.getString("openid" );
        String sessionKey = SessionKeyOpenId.getString( "session_key" );

//        JSONObject userInfo = getUserInfo( encrypteData, sessionKey, iv );
        String line = WxBase64.decryptData(encrypteData, sessionKey, iv);
        JSONObject userInfo= JSON.parseObject(line);
        //用户unionId -> username, unionId -> password
        String username = (String) userInfo.get("openId");
        String password = (String) userInfo.get("openId");
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            //用户存在 走基本登陆流程
            String token = memberService.login(username, password);
            return token;
        }
        //用户不存在 创建用户
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        umsMember.setNickname(rawData.getNickName());
        umsMember.setIcon(rawData.getAvatarUrl());
        umsMember.setGender(rawData.getGender());
        umsMember.setCity(rawData.getCity());
        memberMapper.insertSelective(umsMember);
        String token = memberService.login(username, password);
        return token;
    }

    /**
     *获取微信端openid和session_key
     */
    public static JSONObject getSessionKeyOrOpenId(String code){
        //微信端登录code
        String wxCode = code;
//        APPID = "wx788bdef414e66578";
//        SECRET = "5df34df40168609fca84345cdfe0c6fc";
        APPID = "wxfa70aaa4afffeda1";
        SECRET = "9178aa27624f7a6fd7bc34056dea61e0";
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+wxCode+"&grant_type=authorization_code";
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(requestUrl);
        InputStream inputStream=null;
        CloseableHttpResponse httpResponse=null;
        StringBuilder result=new StringBuilder();
        try {
            httpResponse=httpClient.execute(httpGet);
            HttpEntity entity=httpResponse.getEntity();
            inputStream=entity.getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line); //这里需要使用fastjson来提取一下内容
                JSONObject jsonObject= JSON.parseObject(line);

                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *获取加密数据
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init( Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (InvalidParameterSpecException e) {
            System.out.println(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
