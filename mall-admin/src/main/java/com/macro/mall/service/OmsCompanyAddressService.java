package com.macro.mall.service;

import com.macro.mall.dto.OmsCompanyAddressParam;
import com.macro.mall.dto.PmsProductCategoryParam;
import com.macro.mall.model.OmsCompanyAddress;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货地址管Service
 * Created by macro on 2018/10/18.
 */
public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();
    /**
     * 创建收货地址
     */
    @Transactional
    int create(OmsCompanyAddressParam companyAddressParam);

    /**
     * 修改收货地址
     */
    @Transactional
    int update(Long id, OmsCompanyAddressParam companyAddressParam);

    /**
     * 删除收货地址
     */
    int delete(Long id);

    OmsCompanyAddress getItem(Long id);
}
