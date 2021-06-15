package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderItem;
import lombok.Data;

import java.util.List;

/**
 * 包含订单商品信息的订单详情
 * Created by macro on 2018/9/4.
 */
@Data
public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;

    private Integer point;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
