package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.mapper.OmsCompanyAddressMapper;
import com.macro.mall.model.OmsCompanyAddress;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.model.OmsOrderReturnReason;
import com.macro.mall.portal.domain.OmsOrderDetail;
import com.macro.mall.portal.domain.OmsOrderReturnApplyParam;
import com.macro.mall.portal.domain.OrderReturnTo;
import com.macro.mall.portal.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 申请退货管理Controller
 * Created by macro on 2018/10/17.
 */
@Controller
@Api(tags = "申请退货管理", description = "申请退货管理")
@RequestMapping("/returnApply")
public class OmsPortalOrderReturnApplyController {
    @Autowired
    private OmsPortalOrderReturnApplyService returnApplyService;

    @ApiOperation("申请退货/退款")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
        Long count = returnApplyService.create(returnApply);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页获取用户所有售后申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        CommonPage<OmsOrderReturnApply> list = returnApplyService.list(pageNum, pageSize);
        return CommonResult.success(list);
    }

    @ApiOperation("用户撤销申请")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = returnApplyService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("用户删除申请记录")
    @RequestMapping(value = "/userDelete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult userDelete(@PathVariable Long id) {
        int count = returnApplyService.userDelete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("用户更新申请")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete( @RequestBody OmsOrderReturnApply omsOrderReturnApply) {
        int count = returnApplyService.update(omsOrderReturnApply);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取某一申请详情")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult get(@PathVariable Long id) {
        OrderReturnTo returnApply = returnApplyService.get(id);
        if (returnApply != null) {
            return CommonResult.success(returnApply);
        }
        return CommonResult.success(returnApply, "暂无此订单");
    }

    @ApiOperation("获取退款/退货原因")
    @RequestMapping(value = "/getReason", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsOrderReturnReason>> getReason() {
        List<OmsOrderReturnReason> reason = returnApplyService.getReason();
        if (reason != null) {
            return CommonResult.success(reason);
        }
        return CommonResult.failed();
    }

//    @ApiOperation("申请退款")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
//        int count = returnApplyService.create(returnApply);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
}
