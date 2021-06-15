package com.macro.mall.service;

import com.macro.mall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页轮播图管理Service
 * Created by macro on 2018/11/7.
 */
public interface SmsHomeAdvertiseService {
    /**
     * 添加轮播图
     */
    int create(SmsHomeAdvertise advertise);

    /**
     * 批量删除轮播图
     */
    int delete(List<Long> ids);

    /**
     * 修改上、下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取轮播图详情
     */
    SmsHomeAdvertise getItem(Long id);

    /**
     * 更新轮播图
     */
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * 分页查询轮播图
     */
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}
