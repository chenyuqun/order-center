/**  
 * Project Name:trade-service  <br/>
 * File Name:OrderCreateBizz.java  <br/>
 * Package Name:com.zizaike.trade.service  <br/>
 * Date:2016年6月7日上午9:54:02  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.bizz;  

import java.util.List;

import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;


/**  
 * ClassName:OrderCreateBizz <br/>  
 * Function:  服务订单创建业务. <br/>  
 * Date:     2016年6月7日 上午9:54:02 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see      
 */
public interface TradeServiceOrderCreateBizz {
    /**
     * 
     * createServiceOrder:创建服务订单. <br/>  
     *  
     * @author snow.zhang  
     * @param param
     * @return  
     * @since JDK 1.7
     */
    public List<TradeServiceOrder> createServiceBatchOrder(TradeServiceOrderCreateParam param) throws ZZKServiceException;
}
  
