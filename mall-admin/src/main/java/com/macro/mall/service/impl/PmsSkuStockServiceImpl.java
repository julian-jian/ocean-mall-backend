package com.macro.mall.service.impl;

import com.macro.mall.dao.PmsSkuStockDao;
import com.macro.mall.dto.PmsProductAttributeParam;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.mapper.PmsSkuStockMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsSkuStock;
import com.macro.mall.model.PmsSkuStockExample;
import com.macro.mall.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品sku库存管理Service实现类
 * Created by macro on 2018/4/27.
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andSkuCodeLike("%" + keyword + "%");
        }
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        //更新商品的赠送积分
        PmsProduct product = new PmsProduct();
        product.setId(pid);
        Integer lowGiftPoints = 0;
        if (skuStockList.get(0).getGiftPoints() != null) {
            lowGiftPoints = skuStockList.get(0).getGiftPoints();
            for (PmsSkuStock item : skuStockList) {
                if (lowGiftPoints > item.getGiftPoints()) {
                    lowGiftPoints = item.getGiftPoints();
                }
            }
        }
        product.setGiftPoint(lowGiftPoints);
        //更新兑换积分信息
        Integer lowPoints = 0;
        if (skuStockList.get(0).getPoints() != null) {
            lowPoints = skuStockList.get(0).getPoints();
            for (PmsSkuStock item : skuStockList) {
                if (lowPoints > item.getPoints()) {
                    lowPoints = item.getPoints();
                }
            }
            product.setUsePointLimit(lowPoints);
        }
        productMapper.updateByPrimaryKeySelective(product);
        return skuStockDao.replaceList(skuStockList);
    }
}
