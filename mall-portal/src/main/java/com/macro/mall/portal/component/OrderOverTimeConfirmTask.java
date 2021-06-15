package com.macro.mall.portal.component;

import com.macro.mall.portal.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by macro on 2018/8/24.
 * 订单自动完成收货的定时器
 */
@Component
public class OrderOverTimeConfirmTask {
    private Logger LOGGER =LoggerFactory.getLogger(OrderOverTimeConfirmTask.class);
    @Autowired
    private OmsPortalOrderService portalOrderService;

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描自动收货的订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
//    @Scheduled(cron = "0 */1 * * * ?")  //每分钟
    private void cancelTimeOutOrder(){
        Integer count = portalOrderService.confirmReceiveOverTimeOrder();
        LOGGER.info("自动收货订单，并根据sku编号增加销量，自动收货订单数量：{}",count);
    }
}
