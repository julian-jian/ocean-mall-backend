package com.macro.mall.service;

import com.macro.mall.dto.CmsSegmentParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Segment;
import java.util.List;

/**
 * 段位Service
 */
public interface CmsSegmentService {

    /**
     * 新增段位
     */
    CmsSegment addSegment(CmsSegmentParam cmsSegmentParam);


    /**
     * 获取列表
     */
    List<CmsSegment> list(String keyword, Integer pageSize, Integer pageNum, HttpServletRequest request);

    /**
     * 修改
     */
    int update(Long id, CmsSegment admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 获取最高段位数据
     * @return
     */
    CmsSegment getMaxSegment();

}
