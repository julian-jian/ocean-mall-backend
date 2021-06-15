package com.macro.mall.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author CCQ
 * @since 2021-02-22
 */
@ApiModel(value="Segment对象", description="")
public class CmsSegment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "段位名称")
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

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "最后一次更改时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getConsumptionMin() {
        return consumptionMin;
    }

    public void setConsumptionMin(BigDecimal consumptionMin) {
        this.consumptionMin = consumptionMin;
    }

    public BigDecimal getConsumptionMax() {
        return consumptionMax;
    }

    public void setConsumptionMax(BigDecimal consumptionMax) {
        this.consumptionMax = consumptionMax;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(BigDecimal freeShipping) {
        this.freeShipping = freeShipping;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", consumptionMin=" + consumptionMin +
                ", consumptionMax=" + consumptionMax +
                ", discount=" + discount +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
