package com.macro.mall.repository;

import com.macro.mall.dto.MemberProductCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberProductCollectionRepository extends MongoRepository<MemberProductCollection, String> {
    Page<MemberProductCollection> findByMemberId(Long memberId, Pageable pageable);
}
