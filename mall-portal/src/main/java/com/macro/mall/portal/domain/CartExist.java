package com.macro.mall.portal.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CartExist {
    @ApiModelProperty(value = "1-存在 0-不存在")
    private Integer existStatus;

    @ApiModelProperty(value = "商品id")
    private Long cartId;
}
