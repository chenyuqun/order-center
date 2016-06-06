package com.zizaike.trade.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.trade.basetest.BaseTest;
import com.zizaike.trade.common.OrderNoGenUnit;

/**
 * 
 * ClassName: IdGenServiceTest <br/>  
 * Function: 订单生成测试. <br/>  
 * date: 2016年6月2日 下午4:07:09 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public class OrderNoGenUnitTest extends BaseTest {
    @Autowired
    private OrderNoGenUnit orderNoGenUnit;
    @Test(description = "得到订单号")
    public void genOrderId() throws ZZKServiceException {
        System.err.println(orderNoGenUnit.genOrderId(null));
        System.err.println(orderNoGenUnit.genOrderId(null));
        System.err.println(orderNoGenUnit.genOrderId(null));
    }
}
