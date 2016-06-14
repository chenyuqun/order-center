/**  
 * Project Name:trade-service  <br/>
 * File Name:TradeServiceOrderDaoImpl.java  <br/>
 * Package Name:com.zizaike.trade.dao.impl  <br/>
 * Date:2016年6月6日下午7:26:14  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.dao.impl;

import org.springframework.stereotype.Repository;

import com.zizaike.core.framework.mybatis.impl.GenericMyIbatisDao;
import com.zizaike.entity.trade.Pay;
import com.zizaike.entity.trade.PayStatus;
import com.zizaike.trade.dao.PayDao;

/**
 * ClassName:PayDaoImpl <br/>
 * Function: 支付. <br/>
 * Date: 2016年6月6日 下午7:26:14 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class PayDaoImpl extends GenericMyIbatisDao<Pay, Integer> implements PayDao {
    /**
     * 命名空间
     */
    private static final String NAMESPACE = "com.zizaike.trade.dao.PayMapper.";

    @Override
    public void creatPay(Pay pay) {

        this.getSqlSession().insert(NAMESPACE + "insertSelective", pay);

    }

    @Override
    public Pay queryByOutPayNoAndPayStatus(String outPayNo, PayStatus payStatus) {
        Pay pay = new Pay();
        pay.setOutPayNo(outPayNo);
        pay.setPayStatus(payStatus);
        pay = this.getSqlSession().selectOne(NAMESPACE + "queryByOutPayNoAndPayStatus", pay);
        return pay;
    }

    @Override
    public void update(Pay pay) {
        this.getSqlSession().insert(NAMESPACE + "updateByoutPayNoSelective", pay);
    }

}
