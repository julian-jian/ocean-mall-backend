package com.macro.mall.mapper;

import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.CmsSegmentExample;
import com.macro.mall.model.UmsAdmin;

import java.math.BigDecimal;
import java.util.List;

public interface CmsSegmentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CmsSegment record);

    List<CmsSegment> selectByExample(CmsSegmentExample example);

    int insertSelective(CmsSegment record);


    CmsSegment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSegment record);

    int selectByPrimaryKey(CmsSegment record);

    int updateByPrimaryKey(CmsSegment record);
//select * from cms_segment where consumption_min<55 and consumption_max>55
    CmsSegment getByconSumption(BigDecimal consumption);

    CmsSegment getMaxSegment();
}