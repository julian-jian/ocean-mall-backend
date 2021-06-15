package com.macro.mall.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.portal.domain.AccessTokenVo;
import com.macro.mall.portal.domain.MinioUploadDto;
import com.macro.mall.portal.service.QrCodeService;
import com.macro.mall.security.service.RedisService;
import com.macro.mall.util.HttpUtil;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Autowired
    RedisService redisService;
    @Autowired
    PmsProductMapper productMapper;

    @Override
    public String getQr(String path, String scene, int width, Long productId) {
        PmsProduct pmsProduct = productMapper.selectByPrimaryKey(productId);
        System.out.println(productId + "=======");
        if (pmsProduct == null) {
            return null;
        }
        String qrUrl = pmsProduct.getDetailTitle();
        if (StringUtils.isEmpty(qrUrl)) {
            qrUrl = getQrUrl(path, scene, width);
            pmsProduct.setDetailTitle(qrUrl);
            productMapper.updateByPrimaryKeySelective(pmsProduct);
            return qrUrl;
        } else {
            return qrUrl;
        }

    }

    private String getQrUrl(String path, String scene, int width) {
        String token = (String) redisService.get("access_token");
//        String token = null;
        if (token == null) {
            String accessToken = getAccessToken();
            redisService.set("access_token", accessToken, 7000);
            token = accessToken;
        }
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;

        String fileName = ".png";
        String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());// 文件后缀
        fileName = scene + fileF;// 新的文件名
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + token;
            Map<String, Object> param = new HashMap<>();
            param.put("path", path);
//            param.put("scene", scene);
//            param.put("width", width);
//            param.put("auto_color", false);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class,
                    new Object[0]);
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            String upload = upload(inputStream, fileName);
            return upload;

        } catch (Exception e) {
            LOGGER.error("调用小程序生成微信永久二维码URL接口异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getAccessToken () {
        String apiurl = "https://api.weixin.qq.com/cgi-bin/token";
        String appid = "wxfa70aaa4afffeda1";
        String secret = "9178aa27624f7a6fd7bc34056dea61e0";
        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", apiurl, appid, secret);
        String s = HttpUtil.doGetStr(turl);
        AccessTokenVo parse = JSON.parseObject(s, AccessTokenVo.class);
        return parse.getAccess_token();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(QrCodeServiceImpl.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;
    @Value("${minio.useEndPoint}")
    private String USE_END_POINT;

    public String upload(InputStream file, String filename) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                LOGGER.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file, "image/png");
            LOGGER.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(USE_END_POINT + "/" + BUCKET_NAME + "/" + objectName);
            System.out.println(minioUploadDto.getUrl());
            return minioUploadDto.getUrl();
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return null;
    }
}
