/**  
 * Project Name:trade-service  <br/>
 * File Name:OrderCreateBizz.java  <br/>
 * Package Name:com.zizaike.trade.service  <br/>
 * Date:2016年6月7日上午9:54:02  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.bizz;  

import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.param.TradeServicePayCreateParam;


/**  
 * ClassName:OrderCreateBizz <br/>  
 * Function:  服务订单创建业务. <br/>  
 * Date:     2016年6月7日 上午9:54:02 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see      
 */
public interface TradeServiceOrderPayBizz {
    /**
     * 
     * createPayService:创建支付服务. <br/>  
     *  
     * @author snow.zhang  
     * @param param
     * @return
     * @throws ZZKServiceException
     * @since JDK 1.7
     */
    public String pay(TradeServicePayCreateParam param) throws ZZKServiceException;
}
  
