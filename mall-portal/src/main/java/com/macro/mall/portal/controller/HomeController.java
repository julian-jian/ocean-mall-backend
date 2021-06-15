package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductCategory;
import com.macro.mall.model.Ranking;
import com.macro.mall.portal.domain.HomeContentResult;
import com.macro.mall.portal.domain.HomeRemindResult;
import com.macro.mall.portal.domain.LadderListVO;
import com.macro.mall.portal.domain.LeagueTableVO;
import com.macro.mall.portal.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 首页内容管理Controller
 * Created by macro on 2019/1/28.
 */
@Controller
@Api(tags = "首页内容获取", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

//    @ApiOperation("首页内容页信息展示")
//    @RequestMapping(value = "/content", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<HomeContentResult> content() {
//        HomeContentResult contentResult = homeService.content();
//        return CommonResult.success(contentResult);
//    }

    @ApiOperation("获取温馨提示")
    @RequestMapping(value = "/remind", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeRemindResult> remind() {
        HomeRemindResult remindResult = homeService.getRemind();
        return CommonResult.success(remindResult);
    }

    @ApiOperation("首页轮播图展示")
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeContentResult> banner() {
        HomeContentResult contentResult = homeService.content();
        return CommonResult.success(contentResult);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategory>> getProductCateList() {
        List<PmsProductCategory> productCategoryList = homeService.getProductCateList();
        List<PmsProductCategory> collect = productCategoryList.stream()
                .filter(item -> item.getNavStatus() == 1).collect(Collectors.toList());
        List<PmsProductCategory> collect1 = collect.stream().sorted((a, b) -> a.getSort() - b.getSort()).collect(Collectors.toList());
        return CommonResult.success(collect1);
    }

//    @ApiOperation("分页获取推荐商品")
//    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<PmsProduct>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
//                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<PmsProduct> productList = homeService.recommendProductList(pageSize, pageNum);
//        return CommonResult.success(productList);
//    }

    @ApiOperation("获取商品二级分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategory>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategory> productCategoryList = homeService.getProductCateList(parentId);
        return CommonResult.success(productCategoryList);
    }

//    @ApiOperation("根据分类获取专题")
//    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<CmsSubject>> getSubjectList(@RequestParam(required = false) Long cateId,
//                                                         @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
//                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<CmsSubject> subjectList = homeService.getSubjectList(cateId,pageSize,pageNum);
//        return CommonResult.success(subjectList);
//    }

    @ApiOperation("分页获取特价商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> hotProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.hotProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }

    @ApiOperation("分页获取猜你喜欢商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> newProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.newProductList(pageNum,pageSize);
        return CommonResult.success(productList);
    }

    @ApiOperation("天梯榜")
    @RequestMapping(value = "/getLadderList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<LadderListVO>> getLadderList() {
        List<LadderListVO> ladderListVOS = homeService.getLadderList();
        return CommonResult.success(ladderListVOS);
    }

    @ApiOperation("积分榜")
    @RequestMapping(value = "/getLeagueTable", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<LeagueTableVO>> getLeagueTable() {
        List<LeagueTableVO> ladderListVOS = homeService.getLeagueTable();
        return CommonResult.success(ladderListVOS);
    }

    @ApiOperation("获取排行榜段位信息、图标及bannner")
    @RequestMapping(value = "/getBanner/{type}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Ranking>> getBanner(@PathVariable Integer type) {
        List<Ranking> rankingList = homeService.getBanner(type);
        return CommonResult.success(rankingList);
    }
}
