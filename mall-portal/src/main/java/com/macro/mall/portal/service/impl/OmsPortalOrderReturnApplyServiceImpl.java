package com.macro.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.portal.domain.OmsOrderReturnApplyParam;
import com.macro.mall.portal.domain.OrderReturnTo;
import com.macro.mall.portal.service.OmsPortalOrderReturnApplyService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单退货管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;

    @Autowired
    private OmsOrderItemMapper orderItemMapper;

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Autowired
    private OmsOrderReturnReasonMapper reasonMapper;
    @Autowired
    private UmsMemberService memberService;

    @Override
    public Long create(OmsOrderReturnApplyParam returnApply) {
        OmsOrderItemExample example = new OmsOrderItemExample();
        example.createCriteria().andOrderIdEqualTo(returnApply.getOrderId())
                .andProductIdEqualTo(returnApply.getProductId())
                .andIdEqualTo(returnApply.getOrderItemId());
        List<OmsOrderItem> omsOrderItems = orderItemMapper.selectByExample(example);
        if (omsOrderItems.size() <= 0) {
            return (long)0;
        }
        omsOrderItems.stream().map(item -> {
            item.setIsReturnStatus(1);
            orderItemMapper.updateByPrimaryKeySelective(item);
            return 1;
        }).count();

        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApply,realApply);
        realApply.setDeleteStatus(0);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        realApply.setReturnOrderSn(generateReturnSn());
        UmsMember member = memberService.getCurrentMember();
        realApply.setMemberUsername(member.getUsername());
        returnApplyMapper.insert(realApply);
        return realApply.getId();
    }

    @Override
    public CommonPage<OmsOrderReturnApply> list(Integer pageNum, Integer pageSize) {
        UmsMember member = memberService.getCurrentMember();
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderReturnApplyExample returnApplyExample = new OmsOrderReturnApplyExample();
        OmsOrderReturnApplyExample.Criteria criteria = returnApplyExample.createCriteria();

        criteria.andMemberUsernameEqualTo(member.getUsername()).andDeleteStatusNotEqualTo(1);
        returnApplyExample.setOrderByClause("create_time desc");
        List<OmsOrderReturnApply> returnApplyList = returnApplyMapper.selectByExample(returnApplyExample);
        returnApplyList.stream().map(item -> {
            OmsOrder omsOrder = orderMapper.selectByPrimaryKey(item.getOrderId());
            item.setOrderStatus(omsOrder.getStatus());
            return 1;
        }).count();
        CommonPage<OmsOrderReturnApply> returnPage = CommonPage.restPage(returnApplyList);
        //设置分页信息
        CommonPage<OmsOrderReturnApply> resultPage = new CommonPage<>();
        resultPage.setPageNum(returnPage.getPageNum());
        resultPage.setPageSize(returnPage.getPageSize());
        resultPage.setTotal(returnPage.getTotal());
        resultPage.setTotalPage(returnPage.getTotalPage());
        if (CollUtil.isEmpty(returnApplyList)) {
            return resultPage;
        }
        resultPage.setList(returnApplyList);
        return resultPage;
    }

    @Override
    public int delete(Long id) {
        OmsOrderReturnApply returnApply = returnApplyMapper.selectByPrimaryKey(id);
        OmsOrderItemExample example = new OmsOrderItemExample();
        example.createCriteria().andProductIdEqualTo(returnApply.getProductId())
                .andOrderIdEqualTo(returnApply.getOrderId());
        List<OmsOrderItem> omsOrderItems = orderItemMapper.selectByExample(example);
        OmsOrderItem omsOrderItem = new OmsOrderItem();
        omsOrderItem.setId(omsOrderItems.get(0).getId());
        omsOrderItem.setIsReturnStatus(0);
        orderItemMapper.updateByPrimaryKeySelective(omsOrderItem);

        int count = returnApplyMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public int update(OmsOrderReturnApply omsOrderReturnApply) {
        int count = returnApplyMapper.updateByPrimaryKeySelective(omsOrderReturnApply);
        return count;
    }

    @Override
    public OrderReturnTo get(Long id) {
        OmsOrderReturnApply returnApply = returnApplyMapper.selectByPrimaryKey(id);
        OrderReturnTo orderReturnTo = new OrderReturnTo();
        if (returnApply == null) {
            return orderReturnTo;
        }
        if (returnApply.getCompanyAddressId() != null) {
            OmsCompanyAddress companyAddress = companyAddressMapper.
                    selectByPrimaryKey(returnApply.getCompanyAddressId());
            orderReturnTo.setCompanyAddress(companyAddress);
        }
        OmsOrder omsOrder = orderMapper.selectByPrimaryKey(returnApply.getOrderId());
        returnApply.setOrderStatus(omsOrder.getStatus());
        BeanUtils.copyProperties(returnApply, orderReturnTo);
        return orderReturnTo;
    }

    @Override
    public List<OmsOrderReturnReason> getReason() {
        OmsOrderReturnReasonExample reasonExample = new OmsOrderReturnReasonExample();
        reasonExample.createCriteria().andStatusEqualTo(1);
        List<OmsOrderReturnReason> reasons = reasonMapper.selectByExample(reasonExample);
        return reasons;
    }

    @Override
    public int userDelete(Long id) {
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        returnApply.setId(id);
        returnApply.setDeleteStatus(1);
        int count = returnApplyMapper.updateByPrimaryKeySelective(returnApply);
        return count;
    }

    /**
     *生成退款订单号 ro 退款订单标识 + 14 位当前时间 + 2位 随机数
     */
    String generateReturnSn() {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        sb.append("ro");
        sb.append(date);
        sb.append((int)(Math.random()*90+10));
        return sb.toString();
    }
}
