/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDao.java  <br/>
 * Package Name:com.zizaike.trade.dao  <br/>
 * Date:2016年6月6日下午7:17:10  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.dao;  

import java.util.List;

import com.zizaike.entity.trade.TradeServiceOrder;

/**  
 * ClassName:TradeServiceOrderDao <br/>  
 * Function: 交易服务订单. <br/>  
 * Date:     2016年6月6日 下午7:17:10 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface TradeServiceOrderDao {
    /**
     * 
     * insertTradeServiceOrder:生成单个服务订单. <br/>  
     *  
     * @author snow.zhang  
     * @param tradeServiceOrder
     * @return  
     * @since JDK 1.7
     */
    TradeServiceOrder createTradeServiceOrder(TradeServiceOrder tradeServiceOrder);
    /**
     * 
     * saveBatch:批量生成服务订单. <br/>  
     *  
     * @author snow.zhang  
     * @param tradeServiceOrders
     * @return  
     * @since JDK 1.7
     */
    void saveBatch(List<TradeServiceOrder> tradeServiceOrders);
    /**
     * 
     * updateTradeServiceOrder:更新服务订单. <br/>  
     *  
     * @author snow.zhang  
     * @param tradeServiceOrder
     * @return  
     * @since JDK 1.7
     */
    void updateTradeServiceOrder(TradeServiceOrder tradeServiceOrder);
    
}
  
