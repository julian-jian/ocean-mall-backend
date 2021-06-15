package com.macro.mall.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 首页需要获取的订单数据
 */
@Data
@ToString
public class OrderDataTo {
    /**
     * 待付款订单
     */
    private Long waitPayOrder;

    /**
     * 已完成订单
     */
    private Long finishedOrder;

    /**
     * 待确认收货
     */
    private Long waitConfirmOrder;

    /**
     * 待发货
     */
    private Long waitDelivery;

    /**
     * 待处理退款
     */
    private Long waitRefund;

    /**
     * 已发货
     */
    private Long confirmedOrder;

    /**
     * 待处理退货
     */
    private Long waitRefundGoods;
}
