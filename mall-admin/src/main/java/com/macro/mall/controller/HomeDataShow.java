package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.*;
import com.macro.mall.model.ChartDataTo;
import com.macro.mall.service.HomeDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Api(tags = "首页数据展示", description = "首页数据展示")
@Controller
@RequestMapping("/data")
public class HomeDataShow {

    @Autowired
    private HomeDataService homeDataService;

//    @ApiOperation("查询订单总数，销售总额，订单总额")
//    @RequestMapping(value = "/orderData", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getTotalData() {
//        TotalDataTo totalData = homeDataService.getTotalData();
//        return CommonResult.success(totalData);
//    }

    @ApiOperation("查询订单相关待办事项")
    @RequestMapping(value = "/orderData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getOrderData() {
        OrderDataTo orderData = homeDataService.getOrderData();
        return CommonResult.success(orderData);
    }

    @ApiOperation("查询商品总览")
    @RequestMapping(value = "/productData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getProductData() {
        ProductTo productData = homeDataService.getProductData();
        return CommonResult.success(productData);
    }

    @ApiOperation("用户查询总览")
    @RequestMapping(value = "/memberData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getMemberData() {
        MemberTO memberData = homeDataService.getMemberData();
        return CommonResult.success(memberData);
    }

    @ApiOperation("查询总额")
    @RequestMapping(value = "/totalData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getTotalData() {
        TotalDataTo totalData = homeDataService.getTotalData();
        return CommonResult.success(totalData);
    }

    @ApiOperation("查询订单和销售额总览")
    @RequestMapping(value = "/saleData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSaleData() throws ParseException {
        SaleDataTo saleData = homeDataService.getSaleData();
        return CommonResult.success(saleData);
    }

    @ApiOperation("分天查询数据")
    @RequestMapping(value = "/getChartData", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ChartDataTo>>getChartData(@RequestParam("startTime")String startTime,
                                     @RequestParam("endTime")String endTime) {
        List<ChartDataTo> chartData = homeDataService.getChartData(startTime, endTime);
        return CommonResult.success(chartData);
    }
}
