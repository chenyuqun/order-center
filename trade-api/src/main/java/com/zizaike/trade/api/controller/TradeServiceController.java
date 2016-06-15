/**  
 * Project Name:common-api  <br/>
 * File Name:TranslationController.java  <br/>
 * Package Name:com.zizaike.common.api.controller  <br/>
 * Date:2016年1月9日上午11:33:43  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.BusinessOrderStatus;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderQueryParam;
import com.zizaike.is.trade.TradeServiceOrderService;

/**
 * 
 * ClassName: TranslationController <br/>
 * Function: 特色服务交易服务. <br/>
 * date: 2016年6月12日 下午3:18:10 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/trade/service")
public class TradeServiceController extends BaseAjaxController {
    protected Logger LOG = LoggerFactory.getLogger(TradeServiceController.class);
    @Autowired
    TradeServiceOrderService tradeServiceOrderService;

    @RequestMapping(value = "booking", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult booking(@RequestBody TradeServiceBatchOrderCreateParam param) throws ZZKServiceException {
        LOG.info("booking request param{}", JSON.toJSON(param));
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.createTradeServiceBatchOrder(param));
        return responseResult;
    }

    @RequestMapping(value = "/user/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult userQuery(@RequestParam("customerId") Integer customerId,
            @RequestParam("orderStatus") OrderStatus orderStatus,@RequestParam(value="keywords",required=false) String keywords) throws ZZKServiceException {
        LOG.info("user/query request param customerId :{},orderStatus {},keywords:{}", customerId, orderStatus,keywords);
        ResponseResult responseResult = new ResponseResult();
        TradeServiceOrderQueryParam param = new TradeServiceOrderQueryParam();
        param.setCustomerId(customerId);
        param.setOrderStatus(orderStatus);
        responseResult.setInfo(tradeServiceOrderService.queryCustomer(param));
        return responseResult;
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult queryByOrderNo(@RequestParam(value="orderNo",required=true) String orderNo) throws ZZKServiceException {
        LOG.info("query request param orderNo :{}", orderNo);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.queryByOrderNo(orderNo));
        return responseResult;
    }

    @RequestMapping(value = "/business/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult businessQuery(@RequestParam("businessId") Integer businessId,
            @RequestParam("orderStatus") OrderStatus orderStatus, @RequestParam(value="guestName",required=false) String guestName,
            @RequestParam(value="mobile",required=false) String mobile, @RequestParam(value="orderNo",required=false) String orderNo,
            @RequestParam(value="businessOrderStatus") BusinessOrderStatus businessOrderStatus,
            @RequestParam(value="orderTimeBegin",required=false) String orderTimeBegin, @RequestParam(value="orderTimeEnd",required=false) String orderTimeEnd,
            @RequestParam(value="useTimeBegin",required=false) String useTimeBegin, @RequestParam(value="useTimeEnd",required=false) String useTimeEnd)
            throws ZZKServiceException {

        LOG.info(
                "business/query request param businessId :{},orderStatus:{},guestName:{},mobile:{},orderNo:{},businessOrderStatus:{},orderTimeBegin:{},orderTimeEnd:{},useTimeBegin:{},useTimeEnd:{}",
                businessId, orderStatus, guestName, mobile, orderNo, businessOrderStatus, orderTimeBegin, orderTimeEnd,
                useTimeBegin, useTimeEnd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ResponseResult responseResult = new ResponseResult();
        TradeServiceOrderQueryParam param = new TradeServiceOrderQueryParam();
        param.setBusinessId(businessId);
        param.setBusinessOrderStatus(businessOrderStatus);
        param.setGuestName(guestName);
        param.setMobile(mobile);
        param.setOrderNo(orderNo);
        param.setOrderStatus(orderStatus);

        try {
            if (orderTimeBegin != null) {
                param.setOrderTimeBegin(sdf.parse(orderTimeBegin));
            }
            if (orderTimeEnd != null) {
                param.setOrderTimeEnd(sdf.parse(orderTimeEnd));
            }
            if (orderTimeEnd != null) {
                param.setOrderTimeEnd(sdf.parse(orderTimeEnd));
            }
            if (useTimeBegin != null) {
                param.setUseTimeBegin(sdf.parse(useTimeBegin));
            }
            if (useTimeEnd != null) {
                param.setUseTimeEnd(sdf.parse(useTimeEnd));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalParamterException("date parse error");

        }
        responseResult.setInfo(tradeServiceOrderService.queryBusiness(param));
        return responseResult;
    }
}
