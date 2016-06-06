/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeOrderServiceImpl.java  <br/>
 * Package Name:com.zizaike.trade.service.impl  <br/>
 * Date:2016年6月6日下午11:56:38  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.service.impl;  

import java.util.List;
import java.util.Map;

import com.zizaike.core.common.page.Page;
import com.zizaike.core.common.page.PageList;
import com.zizaike.entity.trade.TradeOrder;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.TradeOrderCreateParam;
import com.zizaike.is.trade.TradeOrderService;

/**  
 * ClassName:TradeOrderServiceImpl <br/>  
 * Function: . <br/>  
 * Date:     2016年6月6日 下午11:56:38 <br/>  
 * @author   snow.zhang  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class TradeOrderServiceImpl implements TradeOrderService {

    @Override
    public TradeOrder queryByOrderNo(String orderNo) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public List<TradeServiceOrder> findTradeServiceOrdersByNo(String orderNo) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Map<String, Object> createTradeOrder(TradeOrderCreateParam param) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public Map<String, Object> createTradeServiceOrder(TradeOrderCreateParam param) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public PageList<TradeOrder> findTradeOrdersByType(Page page, Map<String, Object> paramMap) {

        // TODO Auto-generated method stub  
        return null;
    }

    @Override
    public PageList<TradeOrder> findTradeOrdersByStatus(Page page, Map<String, Object> paramMap) {

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
  
