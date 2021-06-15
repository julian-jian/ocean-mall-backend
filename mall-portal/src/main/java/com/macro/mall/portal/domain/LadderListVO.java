package com.macro.mall.portal.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LadderListVO {

    private Long memberId;
    private String icon;
    private String nickname;
    private String userIcon;
    private BigDecimal currentMonthConsumption;
    private String currentMonthRankIcon;
    private Long historyNumIcon;
}
