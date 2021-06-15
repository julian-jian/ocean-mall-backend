package com.macro.mall.portal.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExpressDto {

    /**
     * 快递名字
     */
    private String expTextName;

    /**
     * 快递电话
     */
    private String tel;

    /**
     * 状态 -2 代发货
     */
    private Integer status;

    /**
     * 单号
     */
    private String mailNo;

    /**
     * 地址
     */
    private String address;

    /**
     * 具体信息
     */
    private List<ExpressTo> expressTo;
}
