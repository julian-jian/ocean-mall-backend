package com.macro.mall.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberTO {
    /**
     * 今日新增
     */
    private Long todayAdd;

    /**
     * 昨日新增
     */
    private Long yesterdayAdd;

    /**
     * 本月新增
     */
    private Long monthAdd;

    /**
     * 会员总数
     */
    private Long memberCount;
}
