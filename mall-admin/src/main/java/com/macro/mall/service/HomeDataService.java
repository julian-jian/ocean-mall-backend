package com.macro.mall.service;

import com.macro.mall.dto.*;
import com.macro.mall.model.ChartDataTo;

import java.text.ParseException;
import java.util.List;

public interface HomeDataService {
    /**
     *获得首页订单部分相关的数据
     */
    OrderDataTo getOrderData();

    /**
     *获得首页商品部分相关数据
     */
    ProductTo getProductData();

    /**
     *获得首页用户部分相关数据
     */
    MemberTO getMemberData();

    /**
     *获得首页总数据
     */
    TotalDataTo getTotalData();

    /**
     * 获取销售数据
     * @return
     */
    SaleDataTo getSaleData() throws ParseException;

    /**
     *获取指定天数数据 用于渲染图标
     */
    List<ChartDataTo> getChartData(String startTime, String endTime);
}
