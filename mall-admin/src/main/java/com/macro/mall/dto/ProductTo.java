package com.macro.mall.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductTo {
    /**
     * 已上架
     */
    private Long putOn;
    /**
     * 已下架
     */
    private Long putOut;
    /**
     * 库存紧张
     */
    private Long stockNo;
    /**
     * 全部商品
     */
    private Long count;
}
