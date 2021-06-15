package com.macro.mall.service;

import com.macro.mall.dto.MmsMemberInfoParam;
import com.macro.mall.model.UmsMember;

import java.util.List;

public interface MemberInfoService {
    List<UmsMember> list(MmsMemberInfoParam queryParam, Integer pageSize, Integer pageNum);
}
