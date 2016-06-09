/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderCreateBizzImpl.java  <br/>
 * Package Name:com.zizaike.trade.bizz.impl  <br/>
 * Date:2016年6月7日上午9:59:26  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
*/  
  
package com.zizaike.trade.bizz.impl;  

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zizaike.core.framework.exception.IllegalParamterException;
import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.entity.commodity.AdditionalService;
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
    @Override
    public List<TradeServiceOrder> createServiceBatchOrder(TradeServiceOrderCreateParam param) throws ZZKServiceException {
        // 参数校验
        validateTradeServiceOrderCreateParam(param);
        //TODO 校验库存
        
        //预定服务参数
        List<AdditionalServiceParam> list = param.getServiceList();
        List<AdditionalService> additionalServiceList = new ArrayList<AdditionalService>();
        List<TradeServiceOrder> tradeServiceOrders = new ArrayList<TradeServiceOrder>();
        for (AdditionalServiceParam additionalServiceParam : list) {
            additionalServiceList.add(additionalServiceService.queryByServiceId(additionalServiceParam.getServiceId()));
        }
        //特色服务列表
        for (AdditionalService additionalService : additionalServiceList) {
            TradeServiceOrder tradeServiceOrder = new TradeServiceOrder();
        }
        tradeServiceOrderDao.saveBatch(tradeServiceOrders);
         return null;
    }
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
        //TODO  校验继续
    }

}
  
