package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.mapper.SmsHomeRecommendProductMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.SmsHomeRecommendProduct;
import com.macro.mall.model.SmsHomeRecommendProductExample;
import com.macro.mall.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 首页人气推荐管理Service实现类
 * Created by macro on 2018/11/7.
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Autowired
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @Autowired
    private PmsProductMapper productMapper;
    @Override
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            recommendProductMapper.insert(recommendProduct);
            Long productId = recommendProduct.getProductId();
            PmsProduct pmsProduct = new PmsProduct();
            pmsProduct.setId(productId);
            pmsProduct.setRecommandStatus(1);
            productMapper.updateByPrimaryKeySelective(pmsProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return recommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        ids.stream().map(item -> recommendProductMapper.selectByPrimaryKey(item))
                .map(item -> item.getProductId())
                .map(item -> {
                    PmsProduct pmsProduct = new PmsProduct();
                    pmsProduct.setId(item);
                    pmsProduct.setRecommandStatus(0);
                    productMapper.updateByPrimaryKeySelective(pmsProduct);
                    return 1;
                }).collect(Collectors.toList());
        return recommendProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        return recommendProductMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(productName)){
            criteria.andProductNameLike("%"+productName+"%");
        }
        if(recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return recommendProductMapper.selectByExample(example);
    }
}
