/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderCreateBizzImpl.java  <br/>
 * Package Name:com.zizaike.trade.bizz.impl  <br/>
 * Date:2016年6月7日上午9:59:26  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.bizz.impl;  

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.bean.ResponseResult;
import com.zizaike.core.common.util.AmountUtil;
import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.core.framework.exception.trade.ServiceNotExistException;
import com.zizaike.entity.commodity.AdditionalService;
import com.zizaike.entity.recommend.DestConfig;
import com.zizaike.entity.trade.OrderStatus;
import com.zizaike.entity.trade.TradeServiceOrder;
import com.zizaike.entity.trade.param.AdditionalServiceParam;
import com.zizaike.entity.trade.param.TradeServiceOrderCreateParam;
import com.zizaike.is.commodity.AdditionalServiceService;
import com.zizaike.is.recommend.DestConfigService;
import com.zizaike.trade.bizz.TradeServiceOrderCreateBizz;
import com.zizaike.trade.common.OrderNoGenUnit;
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
    @Autowired
    AdditionalServiceService additionalServiceService;
    @Autowired
    OrderNoGenUnit orderNoGenUnit;
    @Autowired
    DestConfigService destConfigService;
    public void validateTradeServiceOrderCreateParam(TradeServiceOrderCreateParam param) throws IllegalParamterException{
        if(param==null){
            throw new IllegalParamterException("param is not null");
        }
        if(StringUtils.isEmpty(param.getGuestName())){
            throw new IllegalParamterException("gustName is not null");
        }
        if(StringUtils.isEmpty(param.getMobile())){
            throw new IllegalParamterException("mobile is not null");
        }
        if(StringUtils.isEmpty(param.getEmail())){
            throw new IllegalParamterException("email is not null");
        }
        if(StringUtils.isEmpty(param.getIp())){
            throw new IllegalParamterException("ip is not null");
        }
        if(StringUtils.isEmpty(param.getFirstName())){
            throw new IllegalParamterException("first name is not null");
        }
        if(StringUtils.isEmpty(param.getLastName())){
            throw new IllegalParamterException("last name is not null");
        }
        if(param.getCustomerDestId()==null || param.getCustomerDestId()!=0){
            throw new IllegalParamterException("customerDestId is not null");
        }
        if(param.getAdditionalServiceParam()==null){
            throw new IllegalParamterException("additionalServiceParam is not null");
        }
        AdditionalServiceParam additionalServiceParam = param.getAdditionalServiceParam();
        if(additionalServiceParam==null){
            throw new IllegalParamterException("additionalServiceParam is not null");
        }
        if(additionalServiceParam.getServiceId()==null||additionalServiceParam.getServiceId()<=0 ){
            throw new IllegalParamterException("additionalServiceParam.serviceId is not  null or <=0");
        }
        if(additionalServiceParam.getServiceNumber()==null||additionalServiceParam.getServiceNumber()<=0 ){
            throw new IllegalParamterException("additionalServiceParam.getServiceNumber is not  null or <=0");
        }
        if(additionalServiceParam.getUseTime()==null){
            throw new IllegalParamterException("additionalServiceParam.getUseTime is not  null or <=0");
        }
        //TODO  校验继续
    }
    @Override
    public ResponseResult  createServiceOrder(TradeServiceOrderCreateParam param) throws ZZKServiceException {
        ResponseResult responseResult = new ResponseResult();
        // 参数校验
        // validateTradeServiceOrderCreateParam(param);
         //TODO 校验库存
         //预定服务参数
         AdditionalServiceParam additionalServiceParam = param.getAdditionalServiceParam();
         AdditionalService additionalService = additionalServiceService.queryByServiceId(additionalServiceParam.getServiceId());
         if(additionalService==null){
             throw new ServiceNotExistException();
         }
         //特色服务列表
         TradeServiceOrder tradeServiceOrder = new TradeServiceOrder();
         String orderNo = orderNoGenUnit.genServiceOrderId();
         tradeServiceOrder.setOrderNo(orderNo);
         tradeServiceOrder.setUnitId(additionalService.getId()+"");
         tradeServiceOrder.setAdditionalServiceType(additionalService.getAdditionalServiceType());
         tradeServiceOrder.setUnitName(additionalService.getServiceName());
         tradeServiceOrder.setIp(param.getIp());
         tradeServiceOrder.setCustomerProvince(param.getCustomerProvince());
         tradeServiceOrder.setCustomerDestId(param.getCustomerDestId());
         tradeServiceOrder.setBusinessDestId(additionalService.getDestId());
         tradeServiceOrder.setGuestName(param.getGuestName());
         tradeServiceOrder.setFirstName(param.getFirstName());
         tradeServiceOrder.setLastName(param.getLastName());
         tradeServiceOrder.setMobile(param.getMobile());
         tradeServiceOrder.setEmail(param.getEmail());
         tradeServiceOrder.setWechat(param.getWechat());
         tradeServiceOrder.setRemark(param.getRemark());
         tradeServiceOrder.setUnitNumber(param.getAdditionalServiceParam().getServiceNumber());
         tradeServiceOrder.setUnitPrice(additionalService.getPrice());
         tradeServiceOrder.setUseTime(additionalServiceParam.getUseTime());
         DestConfig businessDestConfig = destConfigService.queryByDestId(additionalService.getDestId());
         tradeServiceOrder.setRmbRateBusiness(businessDestConfig.getExchangeRate());
         DestConfig customerDestConfig = destConfigService.queryByDestId(param.getCustomerDestId());
         tradeServiceOrder.setRmbRateCustomer(customerDestConfig.getExchangeRate());
         tradeServiceOrder.setCustomerCurrencyCode(customerDestConfig.getCurrencyCode());
         tradeServiceOrder.setTotalPrice(AmountUtil.toCustomerPrice(param.getAdditionalServiceParam().getServiceNumber()*additionalService.getPrice(), businessDestConfig.getExchangeRate(), customerDestConfig.getExchangeRate()));
         tradeServiceOrder.setOrderStatus(OrderStatus.CREATE);
         tradeServiceOrder.setCreateAt(new Date());
         tradeServiceOrder.setUpdateAt(new Date());
         tradeServiceOrder.setRemark(param.getRemark());
         tradeServiceOrderDao.createTradeServiceOrder(tradeServiceOrder);
         responseResult.setInfo(tradeServiceOrderDao.queryByOrderNo(orderNo));
          return responseResult;
    }

}
  
