/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderCreateBizzImpl.java  <br/>
 * Package Name:com.zizaike.trade.bizz.impl  <br/>
 * Date:2016年6月7日上午9:59:26  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.bizz.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.core.framework.exception.trade.OrderNotFoundException;
import com.zizaike.entity.trade.OrderType;
import com.zizaike.entity.trade.Pay;
import com.zizaike.entity.trade.PayOrder;
import com.zizaike.entity.trade.PayStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderPayParam;
import com.zizaike.trade.bizz.TradeServiceOrderPayBizz;
import com.zizaike.trade.common.TradeGenUnit;
import com.zizaike.trade.dao.PayDao;
import com.zizaike.trade.dao.PayOrderDao;
import com.zizaike.trade.dao.TradeServiceOrderDao;

/**
 * ClassName:TradeServiceOrderCreateBizzImpl <br/>
 * Function: 服务订单创建业务. <br/>
 * Date: 2016年6月7日 上午9:59:26 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class TradeServiceOrderPayBizzImpl implements TradeServiceOrderPayBizz {
    @Autowired
    TradeServiceOrderDao tradeServiceOrderDao;
    @Autowired
    PayOrderDao payOrderDao;
    @Autowired
    PayDao payDao;
    @Autowired
    TradeGenUnit tradeGenUnit;

    public void validateTradeServiceOrderCreateParam(TradeServiceOrderPayParam param)
            throws IllegalParamterException {
        if (param == null) {
            throw new IllegalParamterException("param is not null");
        }
        if (StringUtils.isEmpty(param.getIp())) {
            throw new IllegalParamterException("ip is not null");
        }
        if (param.getOrderNos()==null|| param.getOrderNos().size()==0) {
            throw new IllegalParamterException("orderNos is not null");
        }
        // TODO 校验继续
    }

    @Override
    public String pay(TradeServiceOrderPayParam param) throws ZZKServiceException {
        //参数校验
        validateTradeServiceOrderCreateParam(param);
        //合法性校验
        List<String> orderNos = param.getOrderNos();
        for (String orderNo : orderNos) {
            if (orderNo.startsWith("S")) {
                TradeServiceOrder tradeServiceOrder = tradeServiceOrderDao.queryByOrderNo(orderNo);
                if(tradeServiceOrder==null){
                    throw new OrderNotFoundException();
                }
            }
        }
        
        String outPayNo = tradeGenUnit.genOutPayNo();
        Pay pay = new Pay();
        pay.setIp(param.getIp());
        pay.setOutPayNo(outPayNo);
        pay.setPayStatus(PayStatus.CREATE);
        pay.setCreateAt(new Date());
        pay.setUpdateAt(new Date());
        payDao.creatPay(pay);
        //创建
        for (String orderNo : orderNos) {
           PayOrder payOrder = new PayOrder();
           payOrder.setOrderNo(orderNo);
           payOrder.setCreateAt(new Date());
           payOrder.setOrderType(getOrderType(orderNo));
           payOrder.setOutPayNo(outPayNo);
           payOrder.setUpdateAt(new Date());
           payOrderDao.createPayOrder(payOrder);
        }
        return outPayNo;
    }
    /**
     * 
     * getOrderType:得到订单类型. <br/>  
     *  
     * @author snow.zhang  
     * @param orderNo
     * @return  
     * @since JDK 1.7
     */
    private OrderType getOrderType(String orderNo){
        OrderType orderType = null;
        if (orderNo.startsWith("S")) {
            orderType = OrderType.SERVICE;
        }else{
            orderType = OrderType.ROOM;
        }
        return orderType;
    }

}
