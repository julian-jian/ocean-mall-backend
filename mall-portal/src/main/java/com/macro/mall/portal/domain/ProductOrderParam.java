package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductOrderParam {
    @ApiModelProperty("收货地址ID")
    private Long memberReceiveAddressId;
//    @ApiModelProperty("优惠券ID")
//    private Long couponId;
//    @ApiModelProperty("使用的积分数")
//    private Integer useIntegration;
    @ApiModelProperty("支付方式 0 现金 1 兑换")
    private Integer payType;
    @ApiModelProperty("被选中商品skuID")
    private Long skuId;
    @ApiModelProperty("商品数量")
    private Integer count;
}
