package com.zizaike.trade.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.zizaike.core.framework.exception.ZZKServiceException;
import com.zizaike.trade.basetest.BaseTest;
import com.zizaike.trade.common.IdGenService;

/**
 * 
 * ClassName: IdGenServiceTest <br/>  
 * Function: 号码生成器测试. <br/>  
 * date: 2016年6月2日 下午4:07:09 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public class IdGenServiceTest extends BaseTest {
    @Test(description = "订单生成测试200W,保证不出错")
    public void genOrderId() throws ZZKServiceException, InterruptedException {
        Map<Object, Object> m1 = new HashMap<Object, Object>();
        Map<Object, Object> m2 = new HashMap<Object, Object>();
        IdGenService tdi1 = new IdGenService(2);
        IdGenService tdi2 = new IdGenService(3);
        T1 t1 = new T1(m1, "t1", (long) Math.random() * 10, tdi1);
        T1 t2 = new T1(m2, "t2", (long) Math.random() * 10, tdi2);
        Thread tt1 = new Thread(t1);
        Thread tt2 = new Thread(t2);
        tt1.start();
        tt2.start();
        tt1.join();
        tt2.join();
        System.out.println("m1:");
        workByKeySet(m1);
        System.out.println("");
        System.out.println("m2:");
        workByKeySet(m2);
        System.out.println("");
        System.out.println("diff start");
        diff(m1, m2);
    }
    public  void workByKeySet(Map<Object, Object> map) {
        Set<Object> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
          String s = (String) it.next();
         // System.out.print(map.get(s) + "|");
        }
      }
    
    public  void diff(Map<Object, Object> map1, Map<Object, Object> map2) {
        int d = 0;
        Set<Object> key = map1.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
          String s = (String) it.next();
          if (map2.get(s) != null) {
            d++;
          }
        }
        System.out.println("生成出错:"+d);
      }
    
       
    
    
     class T1 implements Runnable {
        String name;
        long seed;
        private Map<Object, Object> m1 = new HashMap<Object, Object>();
        IdGenService worker;

        public T1(Map<Object, Object> mm1, String name, long seed, IdGenService worker) {
          this.m1 = mm1;
          this.name = name;
          this.seed = seed;
          this.worker = worker;
        }

        @Override
        public void run() {
          int b = 0;
          Object a;
          System.out.println(name + " start:" + new Date().getTime());
          for (int i = 0; i < 2000000; i++) {
            a = worker.nextId(8);

            if (m1.get(a) != null) {
              System.out.println(name + " xxxxx:" + a);
              b++;
            } else {
              m1.put(a, a);
            }
          }
          System.out.println(name + " end:" + b + " ||" + new Date().getTime());
        }
      }
}
