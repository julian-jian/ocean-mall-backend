package com.macro.mall.resource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 统一下单基础配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wxpay")
public class WxPayResource {
    @Value("${wxpay.appId}")
    private String appId;

    @Value("${wxpay.merchantId}")
    private String merchantId;

    @Value("${wxpay.secrectKey}")
    private String secrectKey;

    @Value("${wxpay.notifyUrl}")
    private String notifyUrl;

    @Value("${wxpay.tradeType}")
    private String tradeType;

    @Value("${wxpay.placeOrderUrl}")
    private String placeOrderUrl;

    @Value("${wxpay.spbillCreateIp}")
    private String spbillCreateIp;

    @Value("${wxpay.refoundUrl}")
    private String refoundUrl;
}
