package com.macro.mall.service;


import com.macro.mall.dto.MemberProductCollection;
import com.macro.mall.dto.MmsMemberInfoParam;
import org.springframework.data.domain.Page;

public interface MemberCollectionService {
    /**
     * 分页查询收藏
     */
    Page<MemberProductCollection> list(MmsMemberInfoParam queryParam, Integer pageNum, Integer pageSize);
}
