package com.macro.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CCQ
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UmsMaxSegmentRecords对象", description="")
public class UmsMaxSegmentRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long memberId;


}
