package com.macro.mall.service;

import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.Ranking;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 排名图标Service
 */
public interface CmsRankingService {

    List<Ranking> getList(Integer type);

    int update(Ranking ranking);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    Ranking addRanking(Ranking ranking);

}
