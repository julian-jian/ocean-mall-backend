package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.portal.service.PointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "积分商品获取", description = "首页内容管理")
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;

    @ApiOperation("分页获取积分商城商品")
    @RequestMapping(value = "/pointProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> pointProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                           @RequestParam(value = "min") Integer min,
                                                           @RequestParam(value = "max") Integer max) {
        List<PmsProduct> productList = pointService.getPointList(pageNum,pageSize, min, max);
        return CommonResult.success(productList);
    }
}
