/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDao.java  <br/>
 * Package Name:com.zizaike.trade.dao  <br/>
 * Date:2016年6月6日下午7:17:10  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.dao;  

import com.zizaike.core.framework.springext.database.Master;
import com.zizaike.entity.trade.Pay;

/**  
 * ClassName:TradeServiceOrderDao <br/>  
 * Function: 支付. <br/>  
 * Date:     2016年6月6日 下午7:17:10 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface PayDao {
    /**
     * 
     * creatPay:创建支付. <br/>  
     *  
     * @author snow.zhang  
     * @param pay  
     * @since JDK 1.7
     */
    @Master
    void creatPay(Pay pay);
    
}
  
