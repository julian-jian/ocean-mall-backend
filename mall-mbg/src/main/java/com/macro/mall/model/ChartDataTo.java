package com.macro.mall.model;

import lombok.Data;

@Data
public class ChartDataTo {
    private String date;
    private Long orderCount;
    private Long orderAmount;
}
