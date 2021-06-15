package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.model.OmsOrderReturnReason;
import com.macro.mall.portal.domain.OmsOrderDetail;
import com.macro.mall.portal.domain.OmsOrderReturnApplyParam;
import com.macro.mall.portal.domain.OrderReturnTo;

import java.util.List;

/**
 * 前台订单退货管理Service
 * Created by macro on 2018/10/17.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    Long create(OmsOrderReturnApplyParam returnApply);

    /**
     *获取所有申请
     */
    CommonPage<OmsOrderReturnApply> list(Integer pageNum, Integer pageSize);

    /**
     *撤销申请
     */
    int delete(Long id);

    /**
     *更新申请
     */
    int update(OmsOrderReturnApply omsOrderReturnApply);

    /**
     *得到某一详情
     */
    OrderReturnTo get(Long id);

    List<OmsOrderReturnReason> getReason();

    int userDelete(Long id);

}
