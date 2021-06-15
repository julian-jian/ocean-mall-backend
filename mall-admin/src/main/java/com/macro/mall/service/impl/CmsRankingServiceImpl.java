package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.mapper.CmsRankingMapper;
import com.macro.mall.mapper.CmsSegmentMapper;
import com.macro.mall.model.CmsSegment;
import com.macro.mall.model.CmsSegmentExample;
import com.macro.mall.model.Ranking;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.service.CmsRankingService;
import com.macro.mall.service.CmsSegmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * CmsSegmentService实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class CmsRankingServiceImpl implements CmsRankingService {
    @Autowired
    private CmsRankingMapper cmsRankingMapper;


    @Override
    public List<Ranking> getList(Integer type) {
        return cmsRankingMapper.getList(type);
    }

    @Override
    public int update(Ranking ranking) {
        int count = cmsRankingMapper.updateById(ranking);
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = cmsRankingMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        Date nowDate = new Date();
        ranking.setCreateTime(nowDate);
        ranking.setUpdateTime(nowDate);
        cmsRankingMapper.insert(ranking);
        return ranking;
    }

}
