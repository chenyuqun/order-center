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
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.common.page.Page;
import com.zizaike.core.common.page.PageList;
import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.trade.BusinessOrderStatus;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.AdditionalServiceParam;
import com.zizaike.entity.trade.param.TradeServiceBatchOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.entity.trade.param.TradeServiceOrderNotifyParam;
import com.zizaike.entity.trade.param.TradeServiceOrderPayParam;
import com.zizaike.entity.trade.param.TradeServiceOrderQueryParam;
import com.zizaike.is.trade.TradeServiceOrderService;
import com.zizaike.trade.bizz.TradeServiceOrderCreateBizz;
import com.zizaike.trade.bizz.TradeServiceOrderNotifyBizz;
import com.zizaike.trade.bizz.TradeServiceOrderPayBizz;
import com.zizaike.trade.common.TradeGenUnit;
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
    private TradeServiceOrderPayBizz tradeServiceOrderPayBizz;
    @Autowired
    private TradeServiceOrderNotifyBizz tradeServiceOrderNotifyBizz;
    @Autowired
    private TradeServiceOrderDao tradeServiceOrderDao;
    @Autowired
    private TradeGenUnit orderNoGenUnit;
    @Override
    public ResponseResult createTradeServiceOrder(TradeServiceOrderCreateParam param) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult = tradeServiceOrderCreateBizz.createServiceOrder(param);
        } catch (ZZKServiceException e) {
            responseResult.setCode(e.getErrorCode().getErrorCode());
            responseResult.setMessage(e.getErrorCode().getErrorMsg());
        }
        return responseResult;
    }
    @Override
    public List<ResponseResult> createTradeServiceBatchOrder(TradeServiceBatchOrderCreateParam param) {
        List<ResponseResult> returnList = new ArrayList<ResponseResult>();
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
            tradeServiceOrderCreateParam.setCustomerId(param.getCustomerId());
            tradeServiceOrderCreateParam.setBusinessId(param.getBusinessId());
            tradeServiceOrderCreateParam.setChannel(param.getChannel());
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
    public List<TradeServiceOrder> processOrderNotify(TradeServiceOrderNotifyParam param) throws ZZKServiceException {
        return tradeServiceOrderNotifyBizz.processOrderNotify(param);
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
    @Override
    public String pay(TradeServiceOrderPayParam param) throws ZZKServiceException {
        return tradeServiceOrderPayBizz.pay(param);
    }
    @Override
    public List<TradeServiceOrder> queryCustomerIdAndOrderStatus(Integer customerId, OrderStatus orderStatus) throws ZZKServiceException {
          
       if(customerId==null || customerId<=0){
           throw new IllegalParamterException("customerId is not null or <=0");
       }
       if(orderStatus==null){
           throw new IllegalParamterException("orderStatus is not null or <=0");
       }
       if(orderStatus==orderStatus.ALL){
           orderStatus =null;
       }
        return tradeServiceOrderDao.queryCustomerIdAndOrderStatus(customerId, orderStatus);
    }
    @Override
    public List<TradeServiceOrder> queryBusiness(TradeServiceOrderQueryParam param) throws ZZKServiceException {
        if(param==null){
            throw new IllegalParamterException("param is not null");
        }
        if(param.getBusinessId()==null || param.getBusinessId()<=0){
            throw new IllegalParamterException("param is not null or <=0");
        }
        if(param.getBusinessOrderStatus()==null){
            throw new IllegalParamterException("businessOrderStatus is not null");
        }
        if(param.getBusinessOrderStatus()==BusinessOrderStatus.ALL){
            param.setBusinessOrderStatus(null);
        }
        if(param.getOrderStatus()==null){
            throw new IllegalParamterException("orderStatus is not null");
        }
        if(param.getOrderStatus()==OrderStatus.ALL){
            param.setOrderStatus(null);
        }
        return tradeServiceOrderDao.queryBusiness(param);
    }


}
  
