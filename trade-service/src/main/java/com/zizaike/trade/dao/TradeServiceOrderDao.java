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

import com.zizaike.core.framework.springext.database.Master;
import com.zizaike.core.framework.springext.database.Slave;
import com.zizaike.entity.trade.OrderStatus;
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
    @Master
    void createTradeServiceOrder(TradeServiceOrder tradeServiceOrder);
    /**
     * 
     * updateTradeServiceOrderPaySuccess:支付成功确认. <br/>  
     *  
     * @author snow.zhang  
     * @param updateTradeServiceOrderPaySuccess  
     * @since JDK 1.7
     */
    @Master
    void updateTradeServiceOrderPaySuccess(String orderNo);
    /**
     * 
     * queryByOrderNo:查看服务订单通过订单号. <br/>  
     *  
     * @author snow.zhang  
     * @param OrderNo
     * @return  
     * @since JDK 1.7
     */
    @Master
    TradeServiceOrder queryByOrderNo(String orderNo);
    /**
     * 
     * queryOrderNoAndOrderStatus:查询通过 订单号与订单状态 .<br/>  
     *  
     * @author snow.zhang  
     * @param orderNo
     * @param orderStatus
     * @return  
     * @since JDK 1.7
     */
    @Master
    TradeServiceOrder queryOrderNoAndOrderStatus(String orderNo,OrderStatus orderStatus);
    /**
     * 
     * queryUserIdAndOrderStatus:用户查询订单. <br/>  
     *  
     * @author snow.zhang  
     * @param userId
     * @param orderStatus
     * @return  
     * @since JDK 1.7
     */
    @Slave
    List<TradeServiceOrder> queryCustomerIdAndOrderStatus(Integer customerId,OrderStatus orderStatus);
    
}
  
