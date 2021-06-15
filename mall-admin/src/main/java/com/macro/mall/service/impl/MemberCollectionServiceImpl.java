package com.macro.mall.service.impl;

import com.macro.mall.dto.MemberProductCollection;
import com.macro.mall.dto.MmsMemberInfoParam;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberExample;
import com.macro.mall.repository.MemberProductCollectionRepository;
import com.macro.mall.service.MemberCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {
    @Autowired
    private MemberProductCollectionRepository productCollectionRepository;

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public Page<MemberProductCollection> list(MmsMemberInfoParam queryParam, Integer pageNum, Integer pageSize) {
        if (queryParam.getKeyword() != null && queryParam.getKeyword() != " ") {
            UmsMemberExample umsMemberExample = new UmsMemberExample();
            UmsMemberExample.Criteria criteria = umsMemberExample.createCriteria();
            criteria.andNicknameEqualTo(queryParam.getKeyword());
            List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
            if (umsMembers.size() == 0) {
                return null;
            }
            Sort sort = new Sort(Sort.Direction.DESC,"create_time");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            Page<MemberProductCollection> byMemberId = productCollectionRepository.findByMemberId(umsMembers.get(0).getId(), pageable);
            return byMemberId;
        }
        //排序
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<MemberProductCollection> collectionPage = productCollectionRepository.findAll(pageable);
        return collectionPage;
    }
}
