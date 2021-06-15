package com.macro.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.entity.Data;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.portal.dao.HomeDao;
import com.macro.mall.portal.domain.*;
import com.macro.mall.portal.service.HomeService;
import com.macro.mall.portal.service.UmsMemberService;
import com.macro.mall.portal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类
 * Created by macro on 2019/1/28.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private SmsHomeRemindMapper remindMapper;
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private CmsSubjectMapper subjectMapper;
    @Autowired
    private CmsSegmentMapper cmsSegmentMapper;
    @Autowired
    private CmsRankingMapper cmsRankingMapper;
    @Autowired
    private UmsMaxSegmentRecordsMapper umsMaxSegmentRecordsMapper;

    @Autowired
    private UmsMemberService memberService;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页轮播图
        result.setAdvertiseList(getHomeAdvertiseList());
//        //获取推荐品牌
//        result.setBrandList(homeDao.getRecommendBrandList(0,6));
//        //获取秒杀信息
//        result.setHomeFlashPromotion(getHomeFlashPromotion());
//        //获取新品推荐
//        result.setNewProductList(homeDao.getNewProductList(0,4));
//        //获取人气推荐
//        result.setHotProductList(homeDao.getHotProductList(0,4));
//        //获取推荐专题
//        result.setSubjectList(homeDao.getRecommendSubjectList(0,4));
        return result;
    }

    @Override
    public HomeRemindResult getRemind() {
        HomeRemindResult result = new HomeRemindResult();
        //获取首页温馨提醒
        result.setRemindList(getHomeRemindList());
        return result;
    }

    @Override
    public List<LadderListVO> getLadderList() {
        Date startMonth = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startMonth);
        calendar.add(Calendar.MONTH,1);
        Date endMonth = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String startTime = sdf.format(startMonth) +"-01 00:00:00";
        String endTime = sdf.format(endMonth) +"-01 00:00:00";
        List<LadderListVO> ladderListVOS = homeDao.getLadderList(startTime,endTime);
        List<LadderListVO> resultList = new ArrayList<>();
        List<Ranking> rankingList = cmsRankingMapper.getList(1);
        for (int i=0;i<ladderListVOS.size();i++) {
            ladderListVOS.get(i).setIcon(rankingList.get(i).getImg());
            UmsMember umsMember = memberService.getById(ladderListVOS.get(i).getMemberId());
//            CmsSegment cmsSegment = cmsSegmentMapper.getByconSumption(ladderListVOS.get(i).getCurrentMonthConsumption());
            BigDecimal historyConsumption = umsMember.getHistoryConsumption() == null?BigDecimal.ZERO:umsMember.getHistoryConsumption();
            BigDecimal currentMonthConsumption = umsMember.getCurrentMonthConsumption() == null?BigDecimal.ZERO:umsMember.getCurrentMonthConsumption();

            CmsSegment cmsSegment = cmsSegmentMapper.getByconSumption(historyConsumption.add(currentMonthConsumption));
            ladderListVOS.get(i).setCurrentMonthRankIcon(cmsSegment.getIcon());
            ladderListVOS.get(i).setHistoryNumIcon(umsMaxSegmentRecordsMapper.countByMemberId(ladderListVOS.get(i).getMemberId()));
            ladderListVOS.get(i).setCurrentMonthConsumption(currentMonthConsumption);
            resultList.add(ladderListVOS.get(i));
        }
        return resultList;
    }

    @Override
    public List<LeagueTableVO> getLeagueTable() {
        List<LeagueTableVO> leagueTableVOS = homeDao.getLeagueTable();
        List<LeagueTableVO> resultList = new ArrayList<>();
        List<Ranking> rankingList = cmsRankingMapper.getList(2);
        for (int i=0;i<leagueTableVOS.size();i++) {
            leagueTableVOS.get(i).setIcon(rankingList.get(i).getImg());
            resultList.add(leagueTableVOS.get(i));
        }
        return resultList;
    }

    @Override
    public List<Ranking> getBanner(Integer type) {
        return cmsRankingMapper.getList(type);
    }

    @Override
    public List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        // TODO: 2019/1/29 暂时默认推荐所有商品
        PageHelper.startPage(pageNum,pageSize);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria()
                .andDeleteStatusEqualTo(0)
                .andPublishStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductCategory> getProductCateList(Long parentId) {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria()
                .andShowStatusEqualTo(1)
                .andParentIdEqualTo(parentId);
        example.setOrderByClause("sort desc");
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductCategory> getProductCateList() {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria()
                .andShowStatusEqualTo(1);
        example.setOrderByClause("leave asc");
                example.setOrderByClause("sort desc");
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andShowStatusEqualTo(1);
        if(cateId!=null){
            criteria.andCategoryIdEqualTo(cateId);
        }
        return subjectMapper.selectByExample(example);
    }

    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getNewProductList(offset, pageSize);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if(nextSession!=null){
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeGreaterThan(date);
        sessionExample.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    private List<SmsHomeRemind> getHomeRemindList() {
        SmsHomeRemindExample example = new SmsHomeRemindExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return remindMapper.selectByExample(example);
    }

    //根据时间获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andStartDateLessThanOrEqualTo(currDate)
                .andEndDateGreaterThanOrEqualTo(currDate);
        List<SmsFlashPromotion> flashPromotionList = flashPromotionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    //根据时间获取秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeLessThanOrEqualTo(currTime)
                .andEndTimeGreaterThanOrEqualTo(currTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }


}
