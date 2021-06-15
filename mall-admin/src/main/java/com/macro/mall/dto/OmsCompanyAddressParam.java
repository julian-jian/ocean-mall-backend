package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class OmsCompanyAddressParam {
    @ApiModelProperty(value = "地址名称")
    @NotEmpty(message = "地址名称不能为空")
    private String addressName;

    @ApiModelProperty(value = "收货人姓名")
    @NotEmpty(message = "收货人姓名不能为空")
    private String name;

    @ApiModelProperty(value = "收货人电话")
    @NotEmpty(message = "收货人电话不能为空")
    private String phone;

    @ApiModelProperty(value = "省/直辖市")
    @NotEmpty(message = "地址名称不能为空")
    private String province;

    @ApiModelProperty(value = "市")
    @NotEmpty(message = "地址名称不能为空")
    private String city;

    @ApiModelProperty(value = "区")
    private String region;

    @ApiModelProperty(value = "详细地址")
    @NotEmpty(message = "地址名称不能为空")
    private String detailAddress;

    @ApiModelProperty(value = "是否设为默认地址")
    private Integer receiveStatus;
}
