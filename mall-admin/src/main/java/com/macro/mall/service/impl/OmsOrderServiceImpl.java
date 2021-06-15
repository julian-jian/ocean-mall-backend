package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.dao.OmsOrderDao;
import com.macro.mall.dao.OmsOrderOperateHistoryDao;
import com.macro.mall.dto.*;
import com.macro.mall.entity.Data;
import com.macro.mall.entity.ExpressBean;
import com.macro.mall.entity.RefoundResult;
import com.macro.mall.mapper.OmsOrderItemMapper;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.OmsOrderOperateHistoryMapper;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.*;
import com.macro.mall.security.service.RedisService;
import com.macro.mall.service.OmsOrderService;
import com.macro.mall.service.WxPaymentService;
import com.macro.mall.service.YiYuanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单管理Service实现类
 * Created by macro on 2018/10/11.
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmsOrderServiceImpl.class);

    @Autowired
    private YiYuanService yiYuanService;

    @Autowired
    private WxPaymentService wxPaymentService;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsOrderDao orderDao;
    @Autowired
    private OmsOrderOperateHistoryDao orderOperateHistoryDao;
    @Autowired
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;
    @Autowired
    private OmsOrderItemMapper itemMapper;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;
    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Override
    public CommonPage<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<OmsOrder> orderList = orderDao.getList(queryParam);
        CommonPage<OmsOrder> page = CommonPage.restPage(orderList);

        //返回未退货订单
        List<OmsOrder> collect = orderList.stream().map(item -> {
            OmsOrderItemExample example = new OmsOrderItemExample();
            example.createCriteria().andOrderIdEqualTo(item.getId()).andIsReturnStatusEqualTo(0);
            List<OmsOrderItem> orderItems = itemMapper.selectByExample(example);
            if (orderItems.size() != 0) {
                return item;
            } else {
                return null;
            }
        }).filter(item -> item != null).collect(Collectors.toList());

        //将微信用户昵称设置为用户名
        collect.stream().map(item -> {
            Long memberId = item.getMemberId();
            UmsMember umsMember = memberMapper.selectByPrimaryKey(memberId);
            item.setMemberUsername(umsMember.getNickname());
            return 1;
        }).count();

        page.setList(collect);
        page.setPageSize(collect.size());

        return page;
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderDao.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryDao.insertList(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
        int count = orderMapper.updateByExampleSelective(record, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andIdIn(ids);
        return orderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        return orderDao.getDetail(id);
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int cancle(Long orderId) throws Exception {
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (order.getPayType() == 2) {
            //退款 -- 原路返回
            RefoundResult refoundResult = wxRefound(order);
            if (refoundResult.getReturn_code().equals("FAIL")) {
                return 0;
            }
            // 积分下单退还积分
        } else if (order.getPayType() == 1) {
            //获取用户的积分
            UmsMember umsMember = memberMapper.selectByPrimaryKey(order.getMemberId());
            Integer integration = umsMember.getIntegration();

            //扣除积分
            UmsMember record=new UmsMember();
            record.setId(umsMember.getId());
            record.setIntegration(integration + order.getUseIntegration());
            Integer i = memberMapper.updateByPrimaryKeySelective(record);
            if (i <= 0) {
                LOGGER.error("退还积分失败");
                return 0;
            }
            //删除用户缓存
            String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + umsMember.getUsername();
            redisService.del(key);
        }

        //关闭订单-改变状态
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setId(orderId);
        omsOrder.setStatus(4);
        omsOrder.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(omsOrder);
        if (count <= 0) {
            throw new Exception("更新订单状态失败");
        }
        return count;
    }

    @Override
    public List<ExpressTo> getExpress(Long id) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        OmsOrder order = orderMapper.selectByPrimaryKey(id);
        String phone = order.getReceiverPhone();
        String phone1 = phone.substring(7, 11);
        String sn = order.getDeliverySn();
        List<ExpressTo> express = new ArrayList<>();
        if (sn == null) {
            List<Data> data = new ArrayList<>();
            Data create = new Data();
            create.setContext("订单创建成功");
            create.setTime(order.getCreateTime());
            data.add(create);
            Data pay = new Data();
            pay.setContext("订单支付成功");
            pay.setTime(order.getPaymentTime());
            data.add(pay);
            data.stream().map(item -> {
                Date time = item.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formatter.format(time);
                ExpressTo expressTo = new ExpressTo();
                expressTo.setContext(item.getContext());
                expressTo.setTime(format);
                express.add(expressTo);
                return 1;
            }).count();
            return express;
        }
        ExpressBean expressBean = yiYuanService.searchExpress(sn, phone1);
        List<Data> data = expressBean.getShowapi_res_body().getData();
        Data create = new Data();
        create.setContext("订单创建成功");
        create.setTime(order.getCreateTime());
        data.add(create);
        Data pay = new Data();
        pay.setContext("订单支付成功");
        pay.setTime(order.getPaymentTime());
        data.add(pay);
        Collections.reverse(data);
        data.stream().map(item -> {
            Date time = item.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formatter.format(time);
            ExpressTo expressTo = new ExpressTo();
            expressTo.setContext(item.getContext());
            expressTo.setTime(format);
            express.add(expressTo);
            return 1;
        }).count();
        return express;
    }

    RefoundResult wxRefound(OmsOrder order) throws Exception {
        double realPrice = order.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        double refoundAmount = order.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        RefoundResult refoundResult = wxPaymentService.refoundOrder(order.getOrderSn(), order.getId().toString(),
                realPrice, refoundAmount);
        return refoundResult;
    }
}
