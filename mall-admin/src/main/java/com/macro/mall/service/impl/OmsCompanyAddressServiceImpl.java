package com.macro.mall.service.impl;

import com.macro.mall.dto.OmsCompanyAddressParam;
import com.macro.mall.mapper.OmsCompanyAddressMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.OmsCompanyAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service实现类
 * Created by macro on 2018/10/18.
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;
    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }

    @Override
    public int create(OmsCompanyAddressParam companyAddressParam) {
        OmsCompanyAddress companyAddress = new OmsCompanyAddress();
        BeanUtils.copyProperties(companyAddressParam, companyAddress);
        if(companyAddress.getReceiveStatus()==1){
            //先将原来的默认地址去除
            OmsCompanyAddress record = new OmsCompanyAddress();
            record.setReceiveStatus(0);
            OmsCompanyAddressExample addressExample = new OmsCompanyAddressExample();
            addressExample.createCriteria().andAddressNameIsNotNull();
            companyAddressMapper.updateByExampleSelective(record, addressExample);
        }
        int count = companyAddressMapper.insertSelective(companyAddress);
        return count;
    }

    @Override
    public int update(Long id, OmsCompanyAddressParam companyAddressParam) {
        OmsCompanyAddress companyAddress = new OmsCompanyAddress();
        companyAddress.setId(id);
        BeanUtils.copyProperties(companyAddressParam, companyAddress);
        if(companyAddress.getReceiveStatus()==1){
            //先将原来的默认地址去除
            OmsCompanyAddress record = new OmsCompanyAddress();
            record.setReceiveStatus(0);
            OmsCompanyAddressExample addressExample = new OmsCompanyAddressExample();
            addressExample.createCriteria().andAddressNameIsNotNull();
            companyAddressMapper.updateByExampleSelective(record, addressExample);
        }
        int count = companyAddressMapper.updateByPrimaryKeySelective(companyAddress);
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = companyAddressMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public OmsCompanyAddress getItem(Long id) {
        OmsCompanyAddress companyAddress = companyAddressMapper.selectByPrimaryKey(id);
        return companyAddress;
    }
}
