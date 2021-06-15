package com.macro.mall.portal.component;

import com.macro.mall.mapper.CmsSegmentMapper;
import com.macro.mall.mapper.UmsMaxSegmentRecordsMapper;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.UmsMaxSegmentRecords;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by macro on 2018/8/24.
 * 每月的海王信息
 */
@Component
@Slf4j
public class ResetRankingTask {
    @Autowired
    private CmsSegmentMapper segmentMapper;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMaxSegmentRecordsMapper maxSegmentRecordsMapper;

//    @Scheduled(cron = "* * 1 ? * ?")
    /**
     * 每月1号1点执行1次
     */
    @Scheduled(cron = "0 0 1 1 * ?")
    private void cancelTimeOutOrder() {
        log.info("====重新计算每个人的段位======");
        // 获取所有会员信息
        List<UmsMember> umsMemberList = memberService.getAllMember();
        log.info("总人数：{}", umsMemberList.size());
        log.info("====获取最高段位信息======");
        CmsSegment maxSegment = segmentMapper.getMaxSegment();
        umsMemberList.stream().forEach(umsMember -> {
            BigDecimal historyConsumption = umsMember.getHistoryConsumption() == null ? BigDecimal.ZERO : umsMember.getHistoryConsumption();
            BigDecimal currentMonthConsumption = umsMember.getCurrentMonthConsumption() == null ? BigDecimal.ZERO : umsMember.getCurrentMonthConsumption();
            CmsSegment oldCmsSegment = segmentMapper.getByconSumption(historyConsumption.add(currentMonthConsumption));
            log.info("上个月段位信息:{}", oldCmsSegment);
            if(oldCmsSegment.getName().equals(maxSegment.getName())){
                log.info("记录最高段位用户");
                UmsMaxSegmentRecords umsMaxSegmentRecords = new UmsMaxSegmentRecords();
                umsMaxSegmentRecords.setMemberId(umsMember.getId());
                maxSegmentRecordsMapper.insert(umsMaxSegmentRecords);
            }
            CmsSegment newCmsSegment = segmentMapper.selectByPrimaryKey(oldCmsSegment.getId() - 1);
            log.info("继承的段位信息:{}", newCmsSegment);
            if (newCmsSegment != null) {
                log.info("重置历史消费和本月消费");
                memberService.updateHistoryConsumptionAndCurrentMonthConsumption(umsMember.getId(), newCmsSegment.getConsumptionMin(), BigDecimal.ZERO);
            } else {
                // 青铜段位
                if (oldCmsSegment.getId() == 2) {
                    log.info("重置历史消费和本月消费【青铜段位】");
                    memberService.updateHistoryConsumptionAndCurrentMonthConsumption(umsMember.getId(), BigDecimal.ZERO, BigDecimal.ZERO);
                }
            }
        });

    }
}
