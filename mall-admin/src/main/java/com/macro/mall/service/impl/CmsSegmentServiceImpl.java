package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.service.CmsSegmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Segment;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CmsSegmentService实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class CmsSegmentServiceImpl implements CmsSegmentService {
    @Value("${system.admin}")
    private String admin;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CmsSegmentMapper cmsSegmentMapper;

    @Override
    public CmsSegment addSegment(CmsSegmentParam cmsSegmentParam) {
        CmsSegment cmsSegment = new CmsSegment();
        BeanUtils.copyProperties(cmsSegmentParam, cmsSegment);
        Date nowDate = new Date();
        cmsSegment.setCreateTime(nowDate);
        cmsSegment.setUpdateTime(nowDate);
        cmsSegment.setStatus(1);
        cmsSegmentMapper.insert(cmsSegment);
        return cmsSegment;
    }

    @Override
    public List<CmsSegment> list(String keyword, Integer pageSize, Integer pageNum, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String s = token.substring(tokenHead.length());
        String name = jwtTokenUtil.getUserNameFromToken(s);
        PageHelper.startPage(pageNum, pageSize);
        CmsSegmentExample example = new CmsSegmentExample();
        CmsSegmentExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        List<CmsSegment> cmsSegments = cmsSegmentMapper.selectByExample(example);
        if (name.equals(admin)) {
            return cmsSegments;
        }
        return cmsSegments;
    }

    @Override
    public int update(Long id, CmsSegment cmsSegment) {
        cmsSegment.setId(id);
        cmsSegment.setUpdateTime(new Date());
        int count = cmsSegmentMapper.updateByPrimaryKeySelective(cmsSegment);
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = cmsSegmentMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public CmsSegment getMaxSegment() {
        return cmsSegmentMapper.getMaxSegment();
    }
}
