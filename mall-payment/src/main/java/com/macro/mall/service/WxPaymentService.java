package com.macro.mall.service;

import com.macro.mall.entity.AppObject;
import com.macro.mall.entity.PayResult;
import com.macro.mall.entity.PreOrderResult;
import com.macro.mall.entity.RefoundResult;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 微信支付
 */
public interface WxPaymentService {
    /**
     * 调用微信接口进行统一下单
     */
    public PreOrderResult placeOrder(String body, String out_trade_no, double total_fee, String openid) throws Exception;

    /**
     * 获取支付结果
     */
    public PayResult getWxPayResult(InputStream inStream) throws Exception;

    /**
     * 发送对象给前端，用于调起支付api
     */
    public AppObject createAppObject(PreOrderResult preOrderResult);

    /**
     * 发起退款
     * out_trade_no: 商户订单号
     * out_refund_no: 商户退款单号
     * total_fee: 订单金额
     * refund_fee: 退款金额
     */
    public RefoundResult refoundOrder(String out_trade_no, String out_refund_no,
                                      double total_fee, double refund_fee) throws Exception;
}
