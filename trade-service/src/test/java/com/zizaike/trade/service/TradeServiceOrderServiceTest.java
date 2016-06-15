package com.zizaike.trade.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.BusinessOrderStatus;
import com.zizaike.entity.trade.ChannelType;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.AdditionalServiceParam;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderNotifyParam;
import com.zizaike.entity.trade.param.TradeServiceOrderPayParam;
import com.zizaike.entity.trade.param.TradeServiceOrderQueryParam;
import com.zizaike.is.trade.TradeServiceOrderService;
import com.zizaike.trade.basetest.BaseTest;

/**
 * 
 * ClassName: TradeServiceOrderServiceTest <br/>  
 * Function: 服务订单交易. <br/>  
 * date: 2016年6月12日 上午10:01:22 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TradeServiceOrderServiceTest extends BaseTest {
    @Autowired
    private TradeServiceOrderService tradeServiceOrderService;
    @Test(description = "创建订单")
    public void createTradeServiceOrder() throws ZZKServiceException {
        TradeServiceOrderCreateParam param = new TradeServiceOrderCreateParam();
        AdditionalServiceParam additionalServiceParam = new AdditionalServiceParam();
        additionalServiceParam.setServiceId(7);
        additionalServiceParam.setServiceNumber(2);
        additionalServiceParam.setUseTime(new Date());
        param.setAdditionalServiceParam(additionalServiceParam);
        param.setCustomerDestId(12);
        param.setCustomerId(1234);
        param.setBusinessId(123);
        param.setChannel(ChannelType.IOS);
        param.setCustomerProvince("江苏");
        param.setEmail("493455221@qq.com");
        param.setFirstName("bin");
        param.setLastName("zhang");
        param.setGuestName("张斌");
        param.setIp("127.0.0.1");
        param.setMobile("18521002422");
        param.setRemark("测试");
        param.setWechat("zeuskingzb");
        ResponseResult responseResult = tradeServiceOrderService.createTradeServiceOrder(param);
        System.err.println(JSON.toJSON(responseResult));
    }
    @Test(description = "批量创建订单")
    public void createTradeServiceBatchOrder() throws ZZKServiceException {
        TradeServiceBatchOrderCreateParam param = new TradeServiceBatchOrderCreateParam();
        List<AdditionalServiceParam> list = new ArrayList<AdditionalServiceParam>();
        AdditionalServiceParam additionalServiceParam = new AdditionalServiceParam();
        additionalServiceParam.setServiceId(7);
        additionalServiceParam.setServiceNumber(2);
        additionalServiceParam.setUseTime(new Date());
        list.add(additionalServiceParam);
        AdditionalServiceParam additionalServiceParam1 = new AdditionalServiceParam();
        additionalServiceParam1.setServiceId(10000000);
        additionalServiceParam1.setServiceNumber(2);
        additionalServiceParam1.setUseTime(new Date());
        list.add(additionalServiceParam1);
        param.setServiceList(list);
        param.setCustomerDestId(12);
        param.setCustomerId(1234);
        param.setBusinessId(123);
        param.setChannel(ChannelType.IOS);
        param.setCustomerProvince("江苏");
        param.setEmail("493455221@qq.com");
        param.setFirstName("bin");
        param.setLastName("zhang");
        param.setGuestName("张斌");
        param.setIp("127.0.0.1");
        param.setMobile("18521002422");
        param.setRemark("测试");
        param.setWechat("zeuskingzb");
        System.err.println(JSON.toJSON(param));
        List<ResponseResult> returnLists = tradeServiceOrderService.createTradeServiceBatchOrder(param);
        for (ResponseResult responseResult : returnLists) {
            System.err.println(JSON.toJSON(responseResult));
        }
    }
    @Test(description = "支付")
    public void pay() throws ZZKServiceException {
        TradeServiceOrderPayParam param = new TradeServiceOrderPayParam();
        param.setIp("127.0.0.1");
        List<String> list = new ArrayList<String>();
        list.add("S061344291584205304");
        list.add("S061326290944205410");
        param.setOrderNos(list);
        System.err.println(JSON.toJSON(param));
        String outPayNo = tradeServiceOrderService.pay(param);
        Assert.assertNotNull(outPayNo);
    }
    @Test(description = "支付回调")
    public void processOrderNotify() throws ZZKServiceException {
        TradeServiceOrderNotifyParam param = new TradeServiceOrderNotifyParam();
        param.setOutPayNo("PAY06130237707264220352");
        param.setPayAccount("支付宝");
        param.setPayNo("支付流水号");
        param.setPaySource("支付渠道");
        System.err.println(JSON.toJSON(param));
//        List<TradeServiceOrder> list= tradeServiceOrderService.processOrderNotify(param);
//        Assert.assertNotEquals(list.size(), 0);
    }
    @Test(description = "用户查询服务")
    public void queryCustomer() throws ZZKServiceException {
        TradeServiceOrderQueryParam param = new TradeServiceOrderQueryParam();
        param.setCustomerId(1234);
        param.setOrderStatus(OrderStatus.ALL);
        param.setKeywords("哈哈");
        List<TradeServiceOrder> list = tradeServiceOrderService.queryCustomer(param);
        Assert.assertNotNull(list);
    }
    @Test(description = "商家查询服务")
    public void queryBusiness() throws ZZKServiceException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TradeServiceOrderQueryParam param = new TradeServiceOrderQueryParam();
        String start = "2016-06-15";
        String end = "2016-06-18";
        param.setBusinessId(123);
        param.setBusinessOrderStatus(BusinessOrderStatus.ALL);
        param.setOrderStatus(OrderStatus.ALL);
        param.setOrderTimeBegin(sdf.parse(start));
        param.setOrderTimeEnd(sdf.parse(end));
        List<TradeServiceOrder> list = tradeServiceOrderService.queryBusiness(param);
        Assert.assertNotNull(list);
    }
    @Test(description = "通过订单号查询")
    public void queryOrderNO() throws ZZKServiceException, ParseException {
        TradeServiceOrder tradeServiceOrder= tradeServiceOrderService.queryByOrderNo("S061501685760170228");
        Assert.assertNotNull(tradeServiceOrder);
    }
}
