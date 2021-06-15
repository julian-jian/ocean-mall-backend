package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsHomeRemind;
import com.macro.mall.service.SmsHomeRemindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 首页轮播广告管理Controller
 * Created by macro on 2018/11/7.
 */
@Controller
@Api(tags = "SmsHomeAdvertiseController", description = "首页温馨提示管理")
@RequestMapping("/home/remind")
public class SmsHomeRemindController {
    @Autowired
    private SmsHomeRemindService remindService;

    @ApiOperation("添加温馨提示内容")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsHomeRemind Remind) {
        int count = remindService.create(Remind);
        System.out.printf(String.valueOf(count));
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除轮播图")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = remindService.delete(ids);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = remindService.updateStatus(id, status);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取轮播图详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsHomeRemind> getItem(@PathVariable Long id) {
        SmsHomeRemind Remind = remindService.getItem(id);
        return CommonResult.success(Remind);
    }

    @ApiOperation("修改轮播图")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsHomeRemind Remind) {
        int count = remindService.update(id, Remind);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("分页查询温馨提示")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeRemind>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRemind> RemindList = remindService.list(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(RemindList));
    }
}
