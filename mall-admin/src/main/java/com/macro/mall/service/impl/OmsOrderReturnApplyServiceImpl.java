package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.OmsOrderReturnApplyDao;
import com.macro.mall.dto.OmsOrderReturnApplyResult;
import com.macro.mall.dto.OmsReturnApplyQueryParam;
import com.macro.mall.dto.OmsUpdateStatusParam;
import com.macro.mall.entity.RefoundResult;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.security.service.RedisService;
import com.macro.mall.service.OmsOrderReturnApplyService;
import com.macro.mall.service.WxPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单退货管理Service
 * Created by macro on 2018/10/18.
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyDao returnApplyDao;
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;
    @Autowired
    private WxPaymentService wxPaymentService;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsOrderItemMapper orderItemMapper;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;
    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return returnApplyDao.getList(queryParam);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
        example.createCriteria().andIdIn(ids).andStatusEqualTo(3);
        return returnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) throws Exception {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            // 确认退款
            OmsOrderReturnApply returnOrderApply = returnApplyMapper.selectByPrimaryKey(id);
            if (returnOrderApply.getReturnAmount() == null || returnOrderApply.getReturnAmount().compareTo(BigDecimal.ZERO) == 0) {
                returnOrderApply.setReturnAmount(statusParam.getReturnAmount());
                returnApplyMapper.updateByPrimaryKeySelective(returnOrderApply);
            }
            OmsOrderReturnApply returnApply1 = returnApplyMapper.selectByPrimaryKey(id);
            RefoundResult refoundResult = wxRefound(returnApply1);
            if (refoundResult.getReturn_code().equals("FAIL")) {
                return 0;
            }
            //完成退货
            returnApply.setId(id);
            if (returnApplyMapper.selectByPrimaryKey(id).getHandleTime() == null) {
                returnApply.setHandleTime(new Date());
                returnApply.setHandleMan(statusParam.getHandleMan());
                returnApply.setHandleNote(statusParam.getHandleNote());
            }
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
            //扣除用户积分 仅在确认收货后扣除
            //1。判断用户是否确认收货了
            OmsOrderExample orderExample = new OmsOrderExample();
            orderExample.createCriteria()
                    .andIdEqualTo(returnApply1.getOrderId())
                    .andStatusEqualTo(3)
                    .andConfirmStatusEqualTo(1);
            List<OmsOrder> omsOrders = orderMapper.selectByExample(orderExample);
            //2。扣除用户积分
            if (omsOrders != null && omsOrders.size() != 0) {
                //获取用户退货商品的积分
                OmsOrderItemExample itemExample = new OmsOrderItemExample();
                itemExample.createCriteria().andOrderIdEqualTo(returnApply1.getOrderId())
                        .andProductIdEqualTo(returnApply1.getProductId());
                List<OmsOrderItem> orderItems = orderItemMapper.selectByExample(itemExample);
                OmsOrderItem orderItem = orderItems.get(0);
                Long skuId = orderItem.getProductSkuId();
                PmsSkuStock pmsSkuStock = skuStockMapper.selectByPrimaryKey(skuId);
                Integer giftPoints = pmsSkuStock.getGiftPoints();

                //获取用户的积分
                UmsMember umsMember = memberMapper.selectByPrimaryKey(omsOrders.get(0).getMemberId());
                Integer integration = umsMember.getIntegration();

                //扣除积分
                UmsMember record=new UmsMember();
                record.setId(umsMember.getId());

                //如积分不足以返还则用退款金额抵扣，每缺少1积分抵扣则按每0.1元1积分抵扣。
                if((integration - giftPoints * orderItem.getProductQuantity())<0){
                    record.setIntegration(0);
                    // 扣除金额（元）
                    BigDecimal deductionAmount = BigDecimal.valueOf((giftPoints * orderItem.getProductQuantity() - integration)*0.1);
                    returnApply.setRefoundAmount(returnApply.getRefoundAmount().subtract(deductionAmount));
                } else {
                    record.setIntegration(integration - giftPoints * orderItem.getProductQuantity());
                }
                // 扣除历史消费,当月消费和积分
                record.setHistoryIntegration((umsMember.getHistoryIntegration()-giftPoints * orderItem.getProductQuantity()));
                record.setHistoryConsumption(umsMember.getHistoryConsumption().subtract(orderItem.getRealAmount()));
                record.setCurrentMonthConsumption(umsMember.getCurrentMonthConsumption().subtract(orderItem.getRealAmount()));
                memberMapper.updateByPrimaryKeySelective(record);

                //删除用户缓存
                String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + umsMember.getUsername();
                redisService.del(key);
            }
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
            //修改子订单为未申请退货
            OmsOrderReturnApply returnApply1 = returnApplyMapper.selectByPrimaryKey(id);
            Long productId = returnApply1.getProductId();
            String orderSn = returnApply1.getOrderSn();
            OmsOrderItemExample itemExample = new OmsOrderItemExample();
            itemExample.createCriteria().andProductIdEqualTo(productId).andOrderSnEqualTo(orderSn);
            List<OmsOrderItem> orderItemList = orderItemMapper.selectByExample(itemExample);
            OmsOrderItem orderItem = orderItemList.get(0);
            orderItem.setIsReturnStatus(0);
            orderItemMapper.updateByPrimaryKeySelective(orderItem);
        }else{
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return returnApplyDao.getDetail(id);
    }

    RefoundResult wxRefound(OmsOrderReturnApply returnOrder) throws Exception {
        OmsOrder omsOrder = orderMapper.selectByPrimaryKey(returnOrder.getOrderId());
        double realPrice = omsOrder.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        double refoundAmount = returnOrder.getReturnAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        RefoundResult refoundResult = wxPaymentService.refoundOrder(returnOrder.getOrderSn(), returnOrder.getReturnOrderSn(),
                realPrice, refoundAmount);
        return refoundResult;
    }
}
