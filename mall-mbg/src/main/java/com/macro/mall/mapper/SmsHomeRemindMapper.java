package com.macro.mall.mapper;

import com.macro.mall.model.SmsHomeRemind;
import com.macro.mall.model.SmsHomeRemindExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsHomeRemindMapper {
    long countByExample(SmsHomeRemindExample example);

    int deleteByExample(SmsHomeRemindExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeRemind record);

    int insertSelective(SmsHomeRemind record);

    List<SmsHomeRemind> selectByExample(SmsHomeRemindExample example);

    SmsHomeRemind selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomeRemind record, @Param("example") SmsHomeRemindExample example);

    int updateByExample(@Param("record") SmsHomeRemind record, @Param("example") SmsHomeRemindExample example);

    int updateByPrimaryKeySelective(SmsHomeRemind record);

    int updateByPrimaryKey(SmsHomeRemind record);
}
