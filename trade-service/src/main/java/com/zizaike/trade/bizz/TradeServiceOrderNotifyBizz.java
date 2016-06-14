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
import com.zizaike.entity.trade.param.TradeServiceOrderNotifyParam;


/**
 * 
 * ClassName: TradeServiceOrderNotifyBizz <br/>  
 * Function: 支付回调. <br/>  
 * date: 2016年6月13日 下午10:28:38 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public interface TradeServiceOrderNotifyBizz {
    /**
     * 
     * processOrderNotify:支付回调. <br/>  
     *  
     * @author snow.zhang  
     * @param param
     * @return
     * @throws ZZKServiceException  
     * @since JDK 1.7
     */
    public List<TradeServiceOrder> processOrderNotify(TradeServiceOrderNotifyParam param) throws ZZKServiceException;
}
  
