package com.macro.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductExample;
import com.macro.mall.portal.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsProduct> getPointList(Integer pageNum, Integer pageSize, Integer min, Integer max) {
        PageHelper.startPage(pageNum,pageSize);
        //获取所有积分可以兑换的商品
        if (min == max) {
            PmsProductExample example = new PmsProductExample();
            example.createCriteria()
                    .andDeleteStatusEqualTo(0)
                    .andPublishStatusEqualTo(1)
                    .andVerifyStatusEqualTo(1);
            return productMapper.selectByExample(example);
        }
        PmsProductExample example = new PmsProductExample();
        example.createCriteria()
                .andDeleteStatusEqualTo(0)
                .andPublishStatusEqualTo(1)
                .andUsePointLimitBetween(min, max)
                .andVerifyStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }
}
