/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderCreateBizzImpl.java  <br/>
 * Package Name:com.zizaike.trade.bizz.impl  <br/>
 * Date:2016年6月7日上午9:59:26  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.bizz.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.trade.bizz.TradeServiceOrderCreateBizz;
import com.zizaike.trade.dao.TradeServiceOrderDao;

/**  
 * ClassName:TradeServiceOrderCreateBizzImpl <br/>  
 * Function: 服务订单创建业务. <br/>  
 * Date:     2016年6月7日 上午9:59:26 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Service
public class TradeServiceOrderCreateBizzImpl implements TradeServiceOrderCreateBizz{
    @Autowired
    TradeServiceOrderDao tradeServiceOrderDao;
    @Override
    public TradeServiceOrder createServiceOrder(TradeServiceOrderCreateParam param) {
        TradeServiceOrder tradeServiceOrder = new TradeServiceOrder();
        
        tradeServiceOrderDao.createTradeServiceOrder(tradeServiceOrder);
         return tradeServiceOrder;
    }

}
  
