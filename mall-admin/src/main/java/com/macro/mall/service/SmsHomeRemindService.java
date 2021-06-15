package com.macro.mall.service;

import com.macro.mall.model.SmsHomeRemind;

import java.util.List;

public interface SmsHomeRemindService {
    /**
     * 添加温馨提示
     */
    int create(SmsHomeRemind remind);

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
    SmsHomeRemind getItem(Long id);

    /**
     * 更新轮播图
     */
    int update(Long id, SmsHomeRemind remind);

    /**
     * 分页查询轮播图
     */
    List<SmsHomeRemind> list(Integer pageSize, Integer pageNum);
}
