package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QrParam {
    @ApiModelProperty("路径")
    private String path;
    @ApiModelProperty("场景参数")
    private String scene;
    @ApiModelProperty("宽度")
    private int width;
    @ApiModelProperty("商品id")
    private String productId;
}
