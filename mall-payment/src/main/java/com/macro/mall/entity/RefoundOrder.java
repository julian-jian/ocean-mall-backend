package com.macro.mall.entity;

import lombok.Data;

/**
 * 申请退款请求参数
 */
@Data
public class RefoundOrder {
    private String appid;// 公众账号ID
    private String mch_id;// 商户号
    private String nonce_str;// 随机字符串
    private String sign;// 签名
    private String out_trade_no; //商户订单号
    private String out_refund_no; //退款单号
    private int total_fee;//订单金额
    private int refund_fee;//退款金额
}
