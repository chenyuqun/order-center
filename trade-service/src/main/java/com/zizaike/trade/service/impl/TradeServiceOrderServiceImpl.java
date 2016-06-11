/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderServiceImpl.java  <br/>
 * Package Name:com.zizaike.trade.service.impl  <br/>
 * Date:2016年6月7日上午9:49:57  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.service.impl;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.common.page.Page;
import com.zizaike.core.common.page.PageList;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.AdditionalServiceParam;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.is.trade.TradeServiceOrderService;
import com.zizaike.trade.bizz.TradeServiceOrderCreateBizz;
import com.zizaike.trade.dao.TradeServiceOrderDao;

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
    @Autowired
    private TradeServiceOrderCreateBizz tradeServiceOrderCreateBizz;
    @Autowired
    private TradeServiceOrderDao tradeServiceOrderDao;
    @Override
    public Map<String, Object> createTradeServiceOrder(TradeServiceOrderCreateParam param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = tradeServiceOrderCreateBizz.createServiceOrder(param);
        } catch (ZZKServiceException e) {
            resultMap.put(e.getErrorCode().getErrorCode(), e.getErrorCode().getErrorMsg());
        }
        return resultMap;
    }
    @Override
    public List<Map<String, Object>> createTradeServiceBatchOrder(TradeServiceBatchOrderCreateParam param) {
        List<Map<String, Object>> returnList = new ArrayList<>();
        List<AdditionalServiceParam> additionalServiceParams = param.getServiceList();
        for (AdditionalServiceParam additionalServiceParam : additionalServiceParams) {
            TradeServiceOrderCreateParam tradeServiceOrderCreateParam = new TradeServiceOrderCreateParam();
            tradeServiceOrderCreateParam.setAdditionalServiceParam(additionalServiceParam);
            tradeServiceOrderCreateParam.setCustomerDestId(param.getCustomerDestId());
            tradeServiceOrderCreateParam.setCustomerProvince(param.getCustomerProvince());
            tradeServiceOrderCreateParam.setEmail(param.getEmail());
            tradeServiceOrderCreateParam.setFirstName(param.getFirstName());
            tradeServiceOrderCreateParam.setGuestName(param.getGuestName());
            tradeServiceOrderCreateParam.setIp(param.getIp());
            tradeServiceOrderCreateParam.setLastName(param.getLastName());
            tradeServiceOrderCreateParam.setMobile(param.getMobile());
            tradeServiceOrderCreateParam.setRemark(param.getRemark());
            tradeServiceOrderCreateParam.setWechat(param.getWechat());
            returnList.add(createTradeServiceOrder(tradeServiceOrderCreateParam));
        }
        return returnList;
    }
    
    @Override
    public TradeServiceOrder queryByOrderNo(String orderNo) {

        return tradeServiceOrderDao.queryByOrderNo(orderNo);
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
  
