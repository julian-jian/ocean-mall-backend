package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MmsMemberInfoParam {
    @ApiModelProperty(value = "用户昵称")
    private String keyword;
    @ApiModelProperty(value = "性别")
    private Integer gender;
}
