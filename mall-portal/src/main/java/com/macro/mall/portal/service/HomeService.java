package com.macro.mall.portal.service;

import com.macro.mall.model.CmsSubject;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductCategory;
import com.macro.mall.model.Ranking;
import com.macro.mall.portal.domain.HomeContentResult;
import com.macro.mall.portal.domain.HomeRemindResult;
import com.macro.mall.portal.domain.LadderListVO;
import com.macro.mall.portal.domain.LeagueTableVO;

import java.util.List;

/**
 * 首页内容管理Service
 * Created by macro on 2019/1/28.
 */
public interface HomeService {

    /**
     * 获取首页轮播图
     */
    HomeContentResult content();

    /**
     * 首页商品推荐
     */
    List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategory> getProductCateList(Long parentId);

    /**
     * 获取首页商品二级分类
     */
    List<PmsProductCategory> getProductCateList();

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取人气推荐商品
     */
    List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取新品推荐商品
     */
    List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);
    /**
     * 获取首页温馨提示
     */
    HomeRemindResult getRemind();

    /**
     * 天梯版
     * @return
     */
    List<LadderListVO> getLadderList();

    /**
     * 积分榜
     * @return
     */
    List<LeagueTableVO> getLeagueTable();

    List<Ranking> getBanner(Integer type);
}
