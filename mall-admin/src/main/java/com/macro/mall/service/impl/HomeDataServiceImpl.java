package com.macro.mall.service.impl;

import com.macro.mall.dao.OmsOrderDao;
import com.macro.mall.dto.*;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.service.HomeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomeDataServiceImpl implements HomeDataService {

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderReturnApplyMapper orderReturnApplyMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private OmsOrderDao orderDao;


    public OrderDataTo getOrderData() {
        OrderDataTo orderDataTo = new OrderDataTo();
        //1.1 获取待付款的订单数量
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        criteria.andStatusEqualTo(0);
        long count = orderMapper.countByExample(omsOrderExample);
        orderDataTo.setWaitPayOrder(count);
        //1.2 已经完成的订单
        OmsOrderExample omsOrderExample2 = new OmsOrderExample();
        OmsOrderExample.Criteria criteria2 = omsOrderExample2.createCriteria();
        criteria2.andDeleteStatusEqualTo(0);
        criteria2.andStatusEqualTo(3);
        long count2 = orderMapper.countByExample(omsOrderExample2);
        System.out.printf("---" + count2 + "---");
        orderDataTo.setFinishedOrder(count2);
        //1.3 待确认
//        OmsOrderExample omsOrderExample3 = new OmsOrderExample();
//        OmsOrderExample.Criteria criteria3 = omsOrderExample3.createCriteria();
//        criteria3.andDeleteStatusEqualTo(0);
//        criteria3.andConfirmStatusEqualTo(0);
//        long count3 = orderMapper.countByExample(omsOrderExample3);
        long count3 = orderDao.getWaitConfirmOrder();
        orderDataTo.setWaitConfirmOrder(count3);
        //1.4 待发货
//        OmsOrderExample omsOrderExample4 = new OmsOrderExample();
//        OmsOrderExample.Criteria criteria4 = omsOrderExample4.createCriteria();
//        criteria4.andDeleteStatusEqualTo(0);
//        criteria4.andStatusEqualTo(1);
//
//        long count4 = orderMapper.countByExample(omsOrderExample4);
        Long count4 = orderDao.getWaitDelivery();
        orderDataTo.setWaitDelivery(count4);
        //1.5 待处理退款 待处理退货1
        OmsOrderReturnApplyExample returnApplyExample5 = new OmsOrderReturnApplyExample();
        OmsOrderReturnApplyExample.Criteria criteria5 = returnApplyExample5.createCriteria();
        criteria5.andStatusEqualTo(0);
        criteria5.andReturnStatusEqualTo(1);
        long count5 = orderReturnApplyMapper.countByExample(returnApplyExample5);
        orderDataTo.setWaitRefund(count5);
        //1.6 已发货
        OmsOrderExample omsOrderExample6 = new OmsOrderExample();
        OmsOrderExample.Criteria criteria6 = omsOrderExample6.createCriteria();
        criteria6.andDeleteStatusEqualTo(0);
        criteria6.andStatusEqualTo(2);
        long count6 = orderMapper.countByExample(omsOrderExample6);
        orderDataTo.setConfirmedOrder(count6);
        //1.7 待处理退货 待处理0
        OmsOrderReturnApplyExample returnApplyExample7 = new OmsOrderReturnApplyExample();
        OmsOrderReturnApplyExample.Criteria criteria7 = returnApplyExample7.createCriteria();
        criteria7.andStatusEqualTo(1);
        criteria7.andReturnStatusEqualTo(0);
        long count7 = orderReturnApplyMapper.countByExample(returnApplyExample7);
        orderDataTo.setWaitRefundGoods(count7);
        return orderDataTo;
    }

    @Override
    public ProductTo getProductData() {
        ProductTo productTo = new ProductTo();
        //1.1 已上架
        PmsProductExample pmsProductExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        criteria.andPublishStatusEqualTo(1);
        long count = productMapper.countByExample(pmsProductExample);
        productTo.setPutOn(count);
        //1.2 已下架
        PmsProductExample pmsProductExample2 = new PmsProductExample();
        PmsProductExample.Criteria criteria2 = pmsProductExample2.createCriteria();
        criteria2.andDeleteStatusEqualTo(0);

        criteria2.andPublishStatusEqualTo(0);
        long count2 = productMapper.countByExample(pmsProductExample2);
        productTo.setPutOut(count2);
        //1.3 库存紧张
        long count3 = productMapper.countLowStock();
        productTo.setStockNo(count3);
        //1.4 全部商品
        PmsProductExample pmsProductExample4 = new PmsProductExample();
        PmsProductExample.Criteria criteria4 = pmsProductExample.createCriteria();
        criteria4.andDeleteStatusEqualTo(0);
        criteria4.andPublishStatusEqualTo(1);
        long count4 = productMapper.countByExample(pmsProductExample4);
        productTo.setCount(count4);
        return productTo;
    }

    @Override
    public MemberTO getMemberData() {
        MemberTO memberTO = new MemberTO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toDay = dateFormat.format(new Date());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String yesterday = dateFormat.format(calendar.getTime());
        SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy-MM");
        String month = dateFormatMonth.format(new Date());

        //1.1 今日新增
        UmsMemberExample memberExample = new UmsMemberExample();
        UmsMemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andCreateTimeGreaterThan(toDay);
        long count = memberMapper.countByExample(memberExample);
        memberTO.setTodayAdd(count);
        //1.2 昨日新增
        UmsMemberExample memberExample2 = new UmsMemberExample();
        UmsMemberExample.Criteria criteria2 = memberExample2.createCriteria();
        criteria2.andStatusEqualTo(1);
        criteria2.andCreateTimeBetween(yesterday, toDay);
        long count2 = memberMapper.countByExample(memberExample2);
        memberTO.setYesterdayAdd(count2);
        //1.3 本月新增
        UmsMemberExample memberExample3 = new UmsMemberExample();
        UmsMemberExample.Criteria criteria3 = memberExample3.createCriteria();
        criteria3.andStatusEqualTo(1);
        criteria3.andCreateTimeGreaterThan(month);
        long count3 = memberMapper.countByExample(memberExample3);
        memberTO.setMonthAdd(count3);
        //1.4 会员总数
        UmsMemberExample memberExample4 = new UmsMemberExample();
        UmsMemberExample.Criteria criteria4 = memberExample4.createCriteria();
        criteria4.andStatusEqualTo(1);
        long count4 = memberMapper.countByExample(memberExample4);
        memberTO.setMemberCount(count4);

        return memberTO;
    }

    @Override
    public TotalDataTo getTotalData() {
        TotalDataTo totalDataTo = new TotalDataTo();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toDay = dateFormat.format(new Date());
        OmsOrderExample orderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        criteria.andCreateTimeGreaterThan(toDay);
        long orderTotal = orderMapper.countByExample(orderExample);
        totalDataTo.setOrderTotal(orderTotal);


        return totalDataTo;
    }

    @Override
    public SaleDataTo getSaleData() throws ParseException {
        SaleDataTo saleDataTo = new SaleDataTo();
        //1.1获取本月订单总数
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria();
        SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy-MM");
        String month = dateFormatMonth.format(new Date());
        criteria.andCreateTimeGreaterThan(month);
        List<Integer> status = new ArrayList<>();
        status.add(1);
        status.add(2);
        status.add(3);
        criteria.andStatusIn(status);
        long monthCount = orderMapper.countByExample(omsOrderExample);
        saleDataTo.setMonthOrders(monthCount);

        //1.2获取本周订单总数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        Date parse = sdf.parse(format);
        Date monday = getThisWeekMonday(parse);
        String format1 = sdf.format(monday);
        OmsOrderExample omsOrderExample1 = new OmsOrderExample();
        OmsOrderExample.Criteria criteria1 = omsOrderExample1.createCriteria();
        criteria1.andCreateTimeGreaterThan(format1);
        criteria1.andStatusIn(status);
        long weekCount = orderMapper.countByExample(omsOrderExample1);
        saleDataTo.setWeekOrders(weekCount);

        //1.3获取本月销售总额
        long monthSales = orderMapper.sumByData(month);
        saleDataTo.setMonthSales(monthSales);

        //1.4获取本周销售总额
        long weekSales = orderMapper.sumByData(format1);
        saleDataTo.setWeekSales(weekSales);

        //1.5获取今日订单总数
        OmsOrderExample omsOrderExample2 = new OmsOrderExample();
        OmsOrderExample.Criteria criteria2 = omsOrderExample2.createCriteria();
        criteria2.andCreateTimeGreaterThan(format);
        criteria2.andStatusIn(status);
        long todayCount = orderMapper.countByExample(omsOrderExample2);
        saleDataTo.setTodayOrders(todayCount);

        //1.6获取今日销售额
        long todaySales = orderMapper.sumByData(format);
        saleDataTo.setTodaySales(todaySales);
        return saleDataTo;
    }

    @Override
    public List<ChartDataTo> getChartData(String startTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(Long.parseLong(startTime));
        Date date2 = new Date(Long.parseLong(endTime));
        String start = format.format(date1);
        String end = format.format(date2);
        List<ChartDataTo> chartData = orderMapper.selectByDay(start, end);
        if (chartData == null || chartData.size() == 0) {
            return null;
        }
        return chartData;
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

}
