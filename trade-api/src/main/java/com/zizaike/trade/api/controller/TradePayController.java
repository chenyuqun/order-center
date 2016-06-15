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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.PayStatus;
import com.zizaike.entity.trade.param.TradeServiceOrderNotifyParam;
import com.zizaike.entity.trade.param.TradeServiceOrderPayParam;
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
@RequestMapping("/trade/pay")
public class TradePayController extends BaseAjaxController{
    protected  Logger LOG = LoggerFactory.getLogger(TradePayController.class);
    @Autowired
    TradeServiceOrderService tradeServiceOrderService;
    /**
     * 
     * mergePay:合并支付. <br/>  
     *  
     * @author snow.zhang  
     * @param param
     * @return
     * @throws ZZKServiceException  
     * @since JDK 1.7
     */
    @RequestMapping(value = "mergePay",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult mergePay(@RequestBody TradeServiceOrderPayParam param) throws ZZKServiceException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.pay(param));
        return responseResult;
    }
    @RequestMapping(value = "callBack",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult mergePay(@RequestBody TradeServiceOrderNotifyParam param) throws ZZKServiceException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.processOrderNotify(param));
        return responseResult;
    }
    @RequestMapping(value = "query",method= RequestMethod.GET)
    @ResponseBody
    public ResponseResult query(@RequestParam("outPayNo") String outPayNo,@RequestParam("payStatus") PayStatus payStatus) throws ZZKServiceException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setInfo(tradeServiceOrderService.queryPayTradeOrders(outPayNo, payStatus));
        return responseResult;
    }
}
  
