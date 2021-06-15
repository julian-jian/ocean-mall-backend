package com.macro.mall.portal.component;

import com.macro.mall.mapper.CmsSegmentMapper;
import com.macro.mall.mapper.UmsMaxSegmentRecordsMapper;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.UmsMaxSegmentRecords;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.LadderListVO;
import com.macro.mall.portal.service.OmsPortalOrderService;
import com.macro.mall.portal.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class RecordHaiwangInfoTask {
@Autowired
private CmsSegmentMapper segmentMapper;
@Autowired
private UmsMemberService memberService;
@Autowired
private UmsMaxSegmentRecordsMapper maxSegmentRecordsMapper;
    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
//    @Scheduled(cron = "1 * * ? * ?")
    private void cancelTimeOutOrder(){
        log.info("====记录海王信息======");
        CmsSegment cmsSegment = segmentMapper.getMaxSegment();
        log.info("最高段位:{},最低消费需满足:{}",cmsSegment.getName(),cmsSegment.getConsumptionMin());
        //再查询上个月满足条件的人
        Date endMonth = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endMonth);
        calendar.add(Calendar.MONTH,-1);
        Date startMonth = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String startTime = sdf.format(startMonth) +"-01 00:00:00";
        String endTime = sdf.format(endMonth) +"-01 00:00:00";
        log.info("统计时间区间:{}-{}",startTime,endTime);
        List<UmsMember> umsMemberList = memberService.getMaxSegmentMember(startTime,endTime,cmsSegment.getConsumptionMin());
        umsMemberList.stream().forEach(umsMember -> {
            UmsMaxSegmentRecords umsMaxSegmentRecords = new UmsMaxSegmentRecords();
            umsMaxSegmentRecords.setMemberId(umsMember.getId());
            maxSegmentRecordsMapper.insert(umsMaxSegmentRecords);
        });
    }
}
