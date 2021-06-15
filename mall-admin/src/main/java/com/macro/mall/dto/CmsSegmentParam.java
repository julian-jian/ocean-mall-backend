package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class CmsSegmentParam {

    @ApiModelProperty(value = "段位名称")
    @NotEmpty(message = "段位名称不能为空")
    private String name;

    @ApiModelProperty(value = "消费区间（最低）")
    private BigDecimal consumptionMin;

    @ApiModelProperty(value = "消费区间（最高）")
    private BigDecimal consumptionMax;

    @ApiModelProperty(value = "折扣")
    private Integer discount;

    @ApiModelProperty(value = "不包邮商品满多少包邮")
    private BigDecimal freeShipping;

    @ApiModelProperty(value = "段位图标")
    private String icon;
}
