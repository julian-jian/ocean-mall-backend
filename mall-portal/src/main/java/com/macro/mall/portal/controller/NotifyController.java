package com.macro.mall.portal.controller;

import com.macro.mall.entity.PayResult;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderExample;
import com.macro.mall.portal.service.OmsPortalOrderService;
import com.macro.mall.service.WxPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/payment/notice")
public class NotifyController {
    @Autowired
    private WxPaymentService wxPaymentService;

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @Autowired
    private OmsOrderMapper orderMapper;
    /**
     * 支付成功后的微信支付异步通知
     */
    @RequestMapping(value="/wxpay")
    public void wxpay(HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 获取微信支付结果
        PayResult payResult =wxPaymentService.getWxPayResult(request.getInputStream());

        boolean isPaid = payResult.getReturn_code().equals("SUCCESS") ? true : false;
        // 查询该笔订单在微信那边是否成功支付
        // 支付成功，商户处理后同步返回给微信参数
        PrintWriter writer = response.getWriter();
        if (isPaid) {

            String merchantOrderId = payResult.getOut_trade_no();			// 商户订单号
            String wxFlowId = payResult.getTransaction_id();
            Integer paidAmount = payResult.getTotal_fee();

            OmsOrderExample omsOrderExample = new OmsOrderExample();
            omsOrderExample.createCriteria().andOrderSnEqualTo(merchantOrderId);
            List<OmsOrder> omsOrders = orderMapper.selectByExample(omsOrderExample);
            if (omsOrders.get(0).getStatus() == 0) {
                portalOrderService.paySuccess(omsOrders.get(0).getId(), omsOrders.get(0).getPayType());
            }
        } else {
            // 支付失败
            String noticeStr = setXML("FAIL", "");
            writer.write(noticeStr);
            writer.flush();
        }
    }

    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }
}
