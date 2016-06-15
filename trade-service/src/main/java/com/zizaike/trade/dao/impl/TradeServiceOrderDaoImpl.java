/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDaoImpl.java  <br/>
 * Package Name:com.zizaike.trade.dao.impl  <br/>
 * Date:2016年6月6日下午7:26:14  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.core.framework.mybatis.impl.GenericMyIbatisDao;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderQueryParam;
import com.zizaike.trade.dao.TradeServiceOrderDao;

/**
 * ClassName:TradeServiceOrderDaoImpl <br/>
 * Function: 服务订单. <br/>
 * Date: 2016年6月6日 下午7:26:14 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class TradeServiceOrderDaoImpl extends GenericMyIbatisDao<TradeServiceOrder, Integer> implements
        TradeServiceOrderDao {
    /**
     * 命名空间
     */
    private static final String NAMESPACE = "com.zizaike.trade.dao.TradeServiceOrderMapper.";

    @Override
    public void createTradeServiceOrder(TradeServiceOrder tradeServiceOrder) {
        this.getSqlSession().insert(NAMESPACE + "insertSelective", tradeServiceOrder);
    }

    @Override
    public TradeServiceOrder queryByOrderNo(String orderNo) {
        return this.getSqlSession().selectOne(NAMESPACE + "queryByOrderNo", orderNo);
    }

    @Override
    public void updateTradeServiceOrderPaySuccess(String orderNo) {
        TradeServiceOrder tradeServiceOrder = new TradeServiceOrder();
        tradeServiceOrder.setOrderNo(orderNo);
        tradeServiceOrder.setOrderStatus(OrderStatus.PAYED);
        tradeServiceOrder.setUpdateAt(new Date());
        this.getSqlSession().update(NAMESPACE + "updateTradeServiceOrderPaySuccess", tradeServiceOrder);
    }

    @Override
    public TradeServiceOrder queryOrderNoAndOrderStatus(String orderNo, OrderStatus orderStatus) {
        TradeServiceOrder tradeServiceOrder = new TradeServiceOrder();
        tradeServiceOrder.setOrderNo(orderNo);
        tradeServiceOrder.setOrderStatus(orderStatus);
        return this.getSqlSession().selectOne(NAMESPACE + "queryOrderNoAndOrderStatus", tradeServiceOrder);
    }

    @Override
    public List<TradeServiceOrder> queryCustomer(TradeServiceOrderQueryParam param) {
        return this.getSqlSession().selectList(NAMESPACE + "queryCustomer", param);
    }

    @Override
    public List<TradeServiceOrder> queryBusiness(TradeServiceOrderQueryParam param) throws ZZKServiceException {
        return this.getSqlSession().selectList(NAMESPACE + "queryBusiness", param);
    }

}
