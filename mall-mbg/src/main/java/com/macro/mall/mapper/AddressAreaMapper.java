package com.macro.mall.mapper;

import com.macro.mall.model.AddressArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressAreaMapper {
    List<AddressArea> selectByPid(@Param("pid") Integer pid);
}
