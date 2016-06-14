/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderNotifyBizzImpl.java  <br/>
 * Package Name:com.zizaike.trade.bizz.impl  <br/>
 * Date:2016年6月13日下午10:42:34  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.bizz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.core.framework.exception.trade.OutOrderNoNotFoundException;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.Pay;
import com.zizaike.entity.trade.PayOrder;
import com.zizaike.entity.trade.PayStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderNotifyParam;
import com.zizaike.trade.bizz.TradeServiceOrderNotifyBizz;
import com.zizaike.trade.dao.PayDao;
import com.zizaike.trade.dao.TradeServiceOrderDao;

/**
 * ClassName:TradeServiceOrderNotifyBizzImpl <br/>
 * Function: 订单回调. <br/>
 * Date: 2016年6月13日 下午10:42:34 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class TradeServiceOrderNotifyBizzImpl implements TradeServiceOrderNotifyBizz {
    @Autowired
    private PayDao payDao;
    @Autowired
    private TradeServiceOrderDao tradeServiceOrderDao;

    public void validateTradeServiceOrderCreateParam(TradeServiceOrderNotifyParam param)
            throws IllegalParamterException {
        if (param == null) {
            throw new IllegalParamterException("param is not null");
        }
        if (StringUtils.isEmpty(param.getOutPayNo())) {
            throw new IllegalParamterException("outPayNo is not null");
        }
        if (StringUtils.isEmpty(param.getPayAccount())) {
            throw new IllegalParamterException("payAccount is not null");
        }
        if (StringUtils.isEmpty(param.getPayNo())) {
            throw new IllegalParamterException("payNo is not null");
        }
        if (StringUtils.isEmpty(param.getPaySource())) {
            throw new IllegalParamterException("paySource is not null");
        }
        // TODO 校验继续
    }

    @Override
    public List<TradeServiceOrder> processOrderNotify(TradeServiceOrderNotifyParam param) throws ZZKServiceException {
        validateTradeServiceOrderCreateParam(param);
        String outPayNo = param.getOutPayNo();
        // 查看是否创建支付申请
        Pay queryPay = payDao.queryByOutPayNoAndPayStatus(outPayNo, PayStatus.CREATE);
        if (queryPay == null) {
            // 对外支付唯一号不存在
            throw new OutOrderNoNotFoundException();
        }
        // 支付成功...
        Pay pay = new Pay();
        pay.setOutPayNo(outPayNo);
        pay.setCallbackTime(new Date());
        pay.setPayAccount(param.getPayAccount());
        pay.setPayNo(param.getPayNo());
        pay.setPaySource(param.getPaySource());
        pay.setPayStatus(PayStatus.PAYED);
        payDao.update(pay);
        // 修改订单状态
        List<PayOrder> payOrders = queryPay.getPayOrders();
        for (PayOrder payOrder : payOrders) {
            tradeServiceOrderDao.updateTradeServiceOrderPaySuccess(payOrder.getOrderNo());
        }
        List<TradeServiceOrder> tradeServiceOrders = new ArrayList<TradeServiceOrder>();
        for (PayOrder payOrder : payOrders) {
            tradeServiceOrders.add(tradeServiceOrderDao.queryOrderNoAndOrderStatus(payOrder.getOrderNo(),OrderStatus.PAYED));
        }
        return tradeServiceOrders;
    }

}
