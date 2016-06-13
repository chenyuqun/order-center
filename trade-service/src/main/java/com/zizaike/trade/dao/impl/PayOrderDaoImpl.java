/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDaoImpl.java  <br/>
 * Package Name:com.zizaike.trade.dao.impl  <br/>
 * Date:2016年6月6日下午7:26:14  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.dao.impl;

import org.springframework.stereotype.Repository;

import com.zizaike.core.framework.mybatis.impl.GenericMyIbatisDao;
import com.zizaike.entity.trade.PayOrder;
import com.zizaike.trade.dao.PayOrderDao;

/**
 * ClassName:PayOrderDaoImpl <br/>
 * Function: 支付记录. <br/>
 * Date: 2016年6月6日 下午7:26:14 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class PayOrderDaoImpl extends GenericMyIbatisDao<PayOrder, Integer> implements
        PayOrderDao {
    /**
     * 命名空间
     */
    private static final String NAMESPACE = "com.zizaike.trade.dao.PayOrderMapper.";

    @Override
    public void createPayOrder(PayOrder payOrder) {
          
       this.getSqlSession().insert(NAMESPACE+"insertSelective", payOrder);
        
    }


}
