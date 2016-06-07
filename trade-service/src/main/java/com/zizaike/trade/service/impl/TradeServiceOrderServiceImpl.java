/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderServiceImpl.java  <br/>
 * Package Name:com.zizaike.trade.service.impl  <br/>
 * Date:2016年6月7日上午9:49:57  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.service.impl;  

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zizaike.core.common.page.Page;
import com.zizaike.core.common.page.PageList;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.is.trade.TradeServiceOrderService;

/**  
 * ClassName:TradeServiceOrderServiceImpl <br/>  
 * Function: 服务订单服务. <br/>  
 * Date:     2016年6月7日 上午9:49:57 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Service
public class TradeServiceOrderServiceImpl implements TradeServiceOrderService {
    
    @Override
    public Map<String, Object> createTradeServiceOrder(TradeServiceOrderCreateParam param) {

        // TODO Auto-generated method stub  
        return null;
    }
    
    @Override
    public TradeServiceOrder queryByOrderNo(String orderNo) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public List<TradeServiceOrder> findTradeServiceOrdersByNo(String orderNo) {

        // TODO Auto-generated method stub  
        return null;
    }

    

    @Override
    public PageList<TradeServiceOrder> findTradeServiceOrdersByMap(Page page, Map<String, Object> paramMap) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public PageList<TradeServiceOrder> findTradeServiceOrdersByStatus(Page page, Map<String, Object> paramMap) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Map<String, Object> processOrderNotify(String orderNo, int payChannel, String payNo, String payAccount) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Map<String, Object> calcRefundAmount(String orderNo, Integer number, String userId) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Map<String, Object> refundTradeServiceOrder(String orderNo, int refundNum, int refundAmount,
            String refundWay, String refundReason, String userId) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Boolean deleteTradeServiceOrder(String orderNo, String userId) {

        // TODO Auto-generated method stub  
        return null;
    }

}
  
