package com.macro.mall.entity;

import lombok.Data;

@Data
public class RefoundResult {
    private String return_code;				// 返回状态码
    private String return_msg;				// 返回信息
    private String result_code;//业务结果
    private String err_code;				// 错误代码
    private String err_code_des;			// 错误代码描述
    private String appid;					// 公众账号ID
    private String mch_id;					// 商户号
    private String nonce_str;				// 随机字符串
    private String sign;					// 签名
    private Integer total_refund_count; //订单总退款次数
    private String transaction_id; //微信订单号
    private String out_trade_no; //商户订单号
    private String out_refund_no;//商户退款单号
    private String refund_id; //微信退款单号
    private String refund_fee; //退款金额
    private String settlement_refund_fee;			// 应结退款金额
    private String refund_channel; //退款渠道
    private String total_fee;				// 标价金额
    private String cash_fee;					// 现金支付金额
    private String cash_refund_fee;				// 现金退款金额
    private String coupon_refund_fee; //总代金券退款金额
    private String refund_status;
    private String coupon_refund_count;
}
