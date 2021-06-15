package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.dto.UmsAdminLoginParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.CmsSegmentService;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 段位设置
 * Created by CCQ on 2021/02/20.
 */
@Controller
@Api(tags = "CmsSegmentController", description = "段位设置")
@RequestMapping("/segment")
@Slf4j
public class CmsSegmentController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private CmsSegmentService cmsSegmentService;

    @ApiOperation(value = "新增段位")
    @RequestMapping(value = "/addSegment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CmsSegment> addSegment(@RequestBody CmsSegmentParam cmsSegmentParam, BindingResult result) {
        log.info("cmsSegmentParam is {}",cmsSegmentParam);
        CmsSegment cmsSegment = cmsSegmentService.addSegment(cmsSegmentParam);
        if (cmsSegment == null) {
            CommonResult.failed();
        }
        return CommonResult.success(cmsSegment);
    }

    @ApiOperation("根据段位名获取列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CmsSegment>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   HttpServletRequest request) {
        List<CmsSegment> cmsSegments = cmsSegmentService.list(keyword, pageSize, pageNum, request);
        return CommonResult.success(CommonPage.restPage(cmsSegments));
    }

    @ApiOperation("修改指定段位信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody CmsSegment cmsSegment) {
        int count = cmsSegmentService.update(id, cmsSegment);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = cmsSegmentService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        CmsSegment cmsSegment = new CmsSegment();
        cmsSegment.setStatus(status);
        int count = cmsSegmentService.update(id,cmsSegment);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
