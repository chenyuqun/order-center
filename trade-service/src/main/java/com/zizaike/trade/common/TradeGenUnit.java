package com.zizaike.trade.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zizaike.core.framework.exception.trade.OrderNotCreateException;
import com.zizaike.core.framework.exception.trade.OutPayNotCreateException;

/**
 * 
 * ClassName: OrderNoGenUnit <br/>  
 * Reason: 交易生成. <br/>  
 * date: 2016年6月2日 下午4:45:16 <br/>  
 *  
 * @author 订单生成服务  
 * @version   
 * @since JDK 1.7
 */
@Repository
public class TradeGenUnit {

    private Logger logger = LoggerFactory.getLogger(TradeGenUnit.class);

    @Autowired
    private IdGenService idGenService;

    public String genOrderId(String preFix) throws OrderNotCreateException {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
            String mmdd = formatter.format(date);
            formatter = new SimpleDateFormat("HHmmss");
            String hhmm = formatter.format(date);
            if (StringUtils.isBlank(preFix)) {
                return new StringBuilder(mmdd).append(idGenService.nextId(8)).append(hhmm).toString();
            } else {
                return new StringBuilder(preFix).append(mmdd).append(idGenService.nextId(8)).append(hhmm).toString();
            }

        } catch (Exception e) {
            logger.error("订单号生成出错 e={}", e.getMessage(), e);
            e.printStackTrace();
            throw new OrderNotCreateException();
        }
    }
    /**
     * 
     * genServiceOrderId:生成服务订单. <br/>  
     *  
     * @author snow.zhang
     * @return
     * @throws OrderNotCreateException  
     * @since JDK 1.7
     */
    public String genServiceOrderId() throws OrderNotCreateException {
       return  genOrderId("S");
    }
    public String genOutPayNo() throws OutPayNotCreateException {
        return  genOutPayNo("PAY");
    }
    /**
     * 
     * genPayOrderNo:生成支付唯一号. <br/>  
     *  
     * @author snow.zhang
     * @param preFix
     * @return
     * @throws OutPayNotCreateException  
     * @since JDK 1.7
     */
    public String genOutPayNo(String preFix) throws OutPayNotCreateException {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
            String mmdd = formatter.format(date);
            formatter = new SimpleDateFormat("HHmmss");
            String hhmm = formatter.format(date);
            if (StringUtils.isBlank(preFix)) {
                return new StringBuilder(mmdd).append(idGenService.nextId(8)).append(hhmm).toString();
            } else {
                return new StringBuilder(preFix).append(mmdd).append(idGenService.nextId(10)).append(hhmm).toString();
            }

        } catch (Exception e) {
            logger.error("对外支付号生成出错 e={}", e.getMessage(), e);
            e.printStackTrace();
            throw new OutPayNotCreateException();
        }
    }
}