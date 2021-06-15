package com.macro.mall.service.impl;

import com.macro.mall.entity.*;
import com.macro.mall.resource.WxPayResource;
import com.macro.mall.service.WxPaymentService;
import com.macro.mall.util.HttpUtil;
import com.macro.mall.util.Sign;
import com.macro.mall.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

@Service
@Slf4j
public class WxPaymentServiceImpl implements WxPaymentService {
    @Autowired
    private WxPayResource wxPayResource;
    /**
     * @param body: 商品描述
     * @param out_trade_no: 商户订单号
     * @param total_fee: 标价金额
     * @return
     * @throws Exception
     */
    @Override
    public PreOrderResult placeOrder(String body, String out_trade_no, double total_fee, String openid) throws Exception {
        body = "智海订单";  // body长度限制是128，不能自由填充，固定处理
        // 生成预付单对象
        PreOrder o = new PreOrder();
        // 生成随机字符串
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        o.setAppid(wxPayResource.getAppId());
        o.setOpenid(openid);
        o.setBody(body);
        o.setMch_id(wxPayResource.getMerchantId());
        o.setNotify_url(wxPayResource.getNotifyUrl());
        o.setOut_trade_no(out_trade_no);
        o.setTotal_fee((int) (total_fee*100));
        o.setNonce_str(nonce_str);
        o.setTrade_type(wxPayResource.getTradeType());
        o.setSpbill_create_ip(wxPayResource.getSpbillCreateIp());
        //统一下单
        SortedMap<Object, Object> p = new TreeMap<Object, Object>();
        p.put("appid", wxPayResource.getAppId());
        p.put("openid", openid);
        p.put("mch_id", wxPayResource.getMerchantId());
        p.put("body", body);
        p.put("nonce_str", nonce_str);
        p.put("out_trade_no", out_trade_no);
        p.put("total_fee", (int) (total_fee*100));
        p.put("spbill_create_ip", wxPayResource.getSpbillCreateIp());
        p.put("notify_url", wxPayResource.getNotifyUrl());
        p.put("trade_type", wxPayResource.getTradeType());
        // 获得签名
        String sign = Sign.createSign("utf-8", p, wxPayResource.getSecrectKey());
        o.setSign(sign);
        // Object转换为XML
        String xml = XmlUtil.object2Xml(o, PreOrder.class);
        // 统一下单地址
        String url = wxPayResource.getPlaceOrderUrl();
        // 调用微信统一下单地址
        String returnXml = HttpUtil.sendPost(url, xml);
        // XML转换为Object
        PreOrderResult preOrderResult = (PreOrderResult) XmlUtil.xml2Object(returnXml, PreOrderResult.class);
        log.info("preOrderResult={}",preOrderResult);
        if ("FAIL".equals(preOrderResult.getReturn_code())) {
            log.info("=========================================交易失败=====================================");
            log.info("错误代码 {},错误原因 {}, 错误描述 {}",preOrderResult.getErr_code(),preOrderResult.getReturn_msg(),
                    preOrderResult.getErr_code_des());
            log.info("交易订单号 {}", out_trade_no);
            log.info("交易金额 {}", (int) (total_fee*100));
            log.info("交易OpenId {}", openid);
            log.info("交易描述 {}", body);
            log.info("====================================================================================");
        }
        return preOrderResult;
    }

    /**
     *获取支付结果
     */
    @Override
    public PayResult getWxPayResult(InputStream inStream) throws Exception {
        BufferedReader in = null;
        String result = "";
        in = new BufferedReader(
                new InputStreamReader(inStream));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        PayResult pr = (PayResult)XmlUtil.xml2Object(result, PayResult.class);

        return pr;
    }

    @Override
    public AppObject createAppObject(PreOrderResult preOrderResult) {
        AppObject appObject = new AppObject();

        String app_package = "prepay_id=" + preOrderResult.getPrepay_id();
        appObject.setApp_package(app_package);

        // 生成随机字符串
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        appObject.setNonceStr(nonce_str);

        appObject.setTimeStamp(Long.toString(System.currentTimeMillis()/1000));
        appObject.setSignType("MD5");

        SortedMap<Object, Object> p = new TreeMap<Object, Object>();
        p.put("appId", wxPayResource.getAppId());
        p.put("nonceStr", nonce_str);
        p.put("package", app_package);
        p.put("signType", "MD5");
        p.put("timeStamp", appObject.getTimeStamp());
        String sign = Sign.createSign("utf-8", p, wxPayResource.getSecrectKey());
        appObject.setPaySign(sign);
        appObject.setTimeStamp(Long.toString(System.currentTimeMillis()/1000));
        appObject.setSignType("MD5");
        return appObject;
    }

    @Override
    public RefoundResult refoundOrder(String out_trade_no, String out_refund_no, double total_fee, double refund_fee) throws Exception {
        RefoundOrder o = new RefoundOrder();
        o.setAppid(wxPayResource.getAppId());
        o.setMch_id(wxPayResource.getMerchantId());
        // 生成随机字符串
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        o.setNonce_str(nonce_str);
        o.setOut_trade_no(out_trade_no);
        o.setOut_refund_no(out_refund_no);
        o.setTotal_fee((int)(total_fee*100));
        o.setRefund_fee((int)(refund_fee * 100));
        SortedMap<Object, Object> p = new TreeMap<Object, Object>();
        p.put("appid", wxPayResource.getAppId());
        p.put("mch_id", wxPayResource.getMerchantId());
        p.put("nonce_str", nonce_str);
        p.put("out_trade_no", out_trade_no);
        p.put("total_fee", (int)(total_fee*100));
        p.put("refund_fee", (int)(refund_fee * 100));
        p.put("out_refund_no", out_refund_no);
        // 获得签名
        String sign = Sign.createSign("utf-8", p, wxPayResource.getSecrectKey());
        o.setSign(sign);
        // Object转换为XML
        String xml = XmlUtil.object2Xml(o, RefoundOrder.class);
        log.info("开始退款，请求参数[{}]", xml);
        // 统一下单地址
        String url = wxPayResource.getRefoundUrl();
        // 调用微信统一下单地址
        String returnXml = HttpUtil.sslSendPost(url, xml);
        log.info("退款结果[{}]", returnXml);
        // XML转换为Object
        RefoundResult refoundOrder = (RefoundResult) XmlUtil.xml2Object(returnXml, RefoundResult.class);
        return refoundOrder;
    }
}
