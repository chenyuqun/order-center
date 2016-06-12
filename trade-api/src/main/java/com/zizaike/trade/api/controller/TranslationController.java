/**  
 * Project Name:common-api  <br/>
 * File Name:TranslationController.java  <br/>
 * Package Name:com.zizaike.common.api.controller  <br/>
 * Date:2016年1月9日上午11:33:43  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.api.controller;  


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.is.trade.TradeServiceOrderService;


/**
 * 
 * ClassName: TranslationController <br/>  
 * Function: 交易服务. <br/>  
 * date: 2016年6月12日 下午3:18:10 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
@Controller
@RequestMapping("/trade/service")
public class TranslationController extends BaseAjaxController{
    protected  Logger LOG = LoggerFactory.getLogger(TranslationController.class);
    @Autowired
    TradeServiceOrderService tradeServiceOrderService;
    @RequestMapping(value = "booking",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult booking(@RequestBody TradeServiceBatchOrderCreateParam param ) throws ZZKServiceException {
       LOG.debug("request param{}",param);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.createTradeServiceBatchOrder(param));
        return responseResult;
    }
}
  