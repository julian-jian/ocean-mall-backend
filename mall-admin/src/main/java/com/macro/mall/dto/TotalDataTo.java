package com.macro.mall.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TotalDataTo {
    //1.1 订单总数
    private Long orderTotal;
    //1.2 今日销售总额
    private Long todaySaleTotal;
    //1.3 昨日销售总额
    private Long yesterdaySaleTotal;
}
