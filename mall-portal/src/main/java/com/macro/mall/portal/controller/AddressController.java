package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.AddressArea;
import com.macro.mall.portal.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/address")
@Controller
@Api(tags = "地址选择省市区", description = "获取省市区")
public class AddressController {

    @Autowired
    AddressService addressService;

    @ApiOperation("获取省分类")
    @RequestMapping(value = "/provinceList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<AddressArea>> getProvinceList(@PathVariable Integer parentId) {

        return CommonResult.success(addressService.getAddressArea(parentId));
    }

    @ApiOperation("获取市分类")
    @RequestMapping(value = "/cityList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<AddressArea>> getCityList(@PathVariable Integer parentId) {

        return CommonResult.success(addressService.getAddressArea(parentId));
    }

    @ApiOperation("获取区分类")
    @RequestMapping(value = "/areaList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<AddressArea>> getAreaList(@PathVariable Integer parentId) {

        if (addressService.getAddressArea(parentId).size() == 0) {
            AddressArea addressArea = new AddressArea();
            List<AddressArea> addressAreaList = new ArrayList<>();
            addressArea.setDistrict(null);
            addressAreaList.add(addressArea);
            return CommonResult.success(addressAreaList);
        }
        return CommonResult.success(addressService.getAddressArea(parentId));
    }

}
