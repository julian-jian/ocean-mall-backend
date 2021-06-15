package com.macro.mall.mapper;

import com.macro.mall.model.UmsMaxSegmentRecords;

public interface UmsMaxSegmentRecordsMapper {

    void insert(UmsMaxSegmentRecords record);

    Long countByMemberId(Long memberId);
}
