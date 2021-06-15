package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeRemindMapper;
import com.macro.mall.model.SmsHomeRemind;
import com.macro.mall.model.SmsHomeRemindExample;
import com.macro.mall.service.SmsHomeRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SmsHomeRemindServiceImpl implements SmsHomeRemindService {
    @Autowired
    private SmsHomeRemindMapper remindMapper;

    @Override
    public int create(SmsHomeRemind remind) {
        remind.setStartTime(new Date());
        remind.setUpdateTime(new Date());
        int a = remindMapper.insert(remind);
        return a;
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRemindExample example = new SmsHomeRemindExample();
        example.createCriteria().andIdIn(ids);
        return remindMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeRemind record = new SmsHomeRemind();
        record.setId(id);
        record.setStatus(status);
        return remindMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SmsHomeRemind getItem(Long id) {
        return remindMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsHomeRemind remind) {
        remind.setId(id);
        remind.setUpdateTime(new Date());
        return remindMapper.updateByPrimaryKeySelective(remind);
    }

    @Override
    public List<SmsHomeRemind> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRemindExample example = new SmsHomeRemindExample();

        example.setOrderByClause("sort desc");
        return remindMapper.selectByExample(example);
    }
}
