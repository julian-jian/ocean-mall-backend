package com.macro.mall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author CCQ
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Ranking对象", description="")
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @ApiModelProperty(value = "图标或者Banner")
    private String img;

    @ApiModelProperty(value = "1:天梯榜;2:积分榜;3:Banner")
    private Integer type;

    private Date createTime;

    private Date updateTime;

}
