package com.macro.mall.model;

import lombok.Data;

@Data
public class AddressArea {

    private Integer id;

    /**
     * 父级地区关系
     */
    private Integer pid;

    /**
     * 地区名称
     */
    private String district;

    /**
     * 子属级别等级
     */
    private Integer level;
}
