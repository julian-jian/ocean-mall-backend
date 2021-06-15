package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.MmsMemberInfoParam;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberExample;
import com.macro.mall.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoServiceImpl implements MemberInfoService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public List<UmsMember> list(MmsMemberInfoParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsMemberExample memberExample = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = memberExample.createCriteria();
        if (queryParam.getKeyword() != null && queryParam.getKeyword() != " ") {
            criteria.andNicknameLike("%" + queryParam.getKeyword() + "%");
        }
        if (queryParam.getGender() != null) {
            criteria.andGenderEqualTo(queryParam.getGender());
        }
        memberExample.setOrderByClause("create_time desc");
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(memberExample);
        return umsMembers;
    }
}
