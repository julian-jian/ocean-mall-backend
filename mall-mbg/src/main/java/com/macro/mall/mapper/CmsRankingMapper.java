package com.macro.mall.mapper;

import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.CmsSegmentExample;
import com.macro.mall.model.Ranking;

import java.util.List;

public interface CmsRankingMapper {

    List<Ranking> getList(Integer type);

    int updateById(Ranking record);

    int deleteByPrimaryKey(Long id);

    int insert(Ranking record);
}