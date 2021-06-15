package com.macro.mall.dto;

import lombok.Data;

@Data
public class SaleDataTo {
    /**
     * 今日订单
     */
    private Long todayOrders;
    /**
     * 今日销售额
     */
    private Long todaySales;

    /**
     * 月订单
     */
    private Long monthOrders;

    /**
     * 周订单
     */
    private Long weekOrders;

    /**
     * 月销售额
     */
    private Long monthSales;

    /**
     * 周销售额
     */
    private Long weekSales;
}
