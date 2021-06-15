package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.AddressAreaMapper;
import com.macro.mall.model.AddressArea;
import com.macro.mall.portal.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressAreaMapper addressAreaMapper;
    @Override
    public List<AddressArea> getAddressArea(Integer id) {
        return addressAreaMapper.selectByPid(id);
    }
}
