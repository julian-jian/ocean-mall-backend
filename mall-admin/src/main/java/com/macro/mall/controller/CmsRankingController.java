package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.Ranking;
import com.macro.mall.service.CmsRankingService;
import com.macro.mall.service.CmsSegmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 排名图标设置
 * Created by CCQ on 2021/02/20.
 */
@Controller
@Api(tags = "CmsRankingController", description = "图标设置")
@RequestMapping("/ranking")
@Slf4j
public class CmsRankingController {

    @Autowired
    private CmsRankingService cmsRankingService;

    @ApiOperation("获取图标或banner")
    @RequestMapping(value = "/getList/{type}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getList(@PathVariable Integer type) {
        List<Ranking> rankingList = cmsRankingService.getList(type);
        return CommonResult.success(rankingList);

    }

    @ApiOperation("修改图标")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody Ranking ranking) {
        int count = cmsRankingService.update(ranking);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除Banner")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = cmsRankingService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "新增Banner")
    @RequestMapping(value = "/addRanking", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Ranking> addRanking(@RequestBody Ranking ranking, BindingResult result) {
        log.info("ranking is {}",ranking);
        cmsRankingService.addRanking(ranking);
        return CommonResult.success(ranking);
    }

}
