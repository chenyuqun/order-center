package com.zizaike.trade.service;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    @Test(description = "支付订单号")
    public void genPayOrderNo() throws ZZKServiceException {
        System.err.println(orderNoGenUnit.genPayOrderNo());
//        Map<String,String> map1 = new HashMap<String,String>();
//        for (int i = 0; i < 2000000; i++) {
//            map1.put(orderNoGenUnit.genPayOrderNo(), orderNoGenUnit.genPayOrderNo());
//        }
//        Map<String,String> map2 = new HashMap<String,String>();
//        for (int i = 0; i < 2000000; i++) {
//            map2.put(orderNoGenUnit.genPayOrderNo(), orderNoGenUnit.genPayOrderNo());
//        }
//        diff(map1,map2);
    }
    
    public  void diff(Map<String, String> map1, Map<String, String> map2) {
        int d = 0;
        Set<String> key = map1.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
          String s = (String) it.next();
          if (map2.get(s) != null) {
            d++;
          }
        }
        System.out.println("生成出错:"+d);
      }
}
