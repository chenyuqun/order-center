/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDaoImpl.java  <br/>
 * Package Name:com.zizaike.trade.dao.impl  <br/>
 * Date:2016年6月6日下午7:26:14  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.dao.impl;  

import java.util.List;

import com.zizaike.core.framework.mybatis.impl.GenericMyIbatisDao;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.trade.dao.TradeServiceOrderDao;

/**  
 * ClassName:TradeServiceOrderDaoImpl <br/>  
 * Function: 服务订单. <br/>  
 * Date:     2016年6月6日 下午7:26:14 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class TradeServiceOrderDaoImpl extends GenericMyIbatisDao<TradeServiceOrder, Integer> implements TradeServiceOrderDao {
    /**
     * 命名空间
     */
    private static final String NAMESPACE = "com.zizaike.trade.dao.TradeServiceOrderMapper." ;
    @Override
    public TradeServiceOrder createTradeServiceOrder(TradeServiceOrder tradeServiceOrder) {
        
         this.getSqlSession().insert(NAMESPACE+"insertSelective", tradeServiceOrder);
         return tradeServiceOrder;
    }

    @Override
    public List<TradeServiceOrder> saveBatch(List<TradeServiceOrder> tradeServiceOrders) {

        
        this.getSqlSession().insert(NAMESPACE+"insertSelective", tradeServiceOrders);
        return tradeServiceOrders;
    }

    @Override
    public void updateTradeServiceOrder(TradeServiceOrder tradeServiceOrder) {
        
        this.getSqlSession().update(NAMESPACE+"updateByOrderNoSelective", tradeServiceOrder);
    }

}
  
