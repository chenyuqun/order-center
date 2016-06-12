package com.zizaike.trade.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.param.AdditionalServiceParam;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
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
}
