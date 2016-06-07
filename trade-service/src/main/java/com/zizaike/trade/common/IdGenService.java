/**  
 * Project Name:trade-service  <br/>
 * File Name:IdGenServiceImpl.java  <br/>
 * Package Name:com.zizaike.trade.service.impl  <br/>
 * Date:2016年6月2日下午3:23:37  <br/>
 * Copyright (c) 2016, zizaike.com All Rights Reserved.  
 *  
 */

package com.zizaike.trade.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * ClassName:IdGenServiceImpl <br/>
 * Function: 随机数生成 测试8位ID 200W内不重复 注意分布式环境下 需要传入不同的workerId. <br/>
 * Date: 2016年6月2日 下午3:23:37 <br/>
 * 
 * @author snow.zhang
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class IdGenService {
    @Value("${idGenService.workerId}")
    private  long workerId;
    private final static long twepoch = 1361753741828L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    // 异或
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;
    public IdGenService() {
    }
    public IdGenService(final long workerId) {
        this.workerId = workerId;
    }

    /**
     * @param length 小于16位
     * @return
     */
    public synchronized String nextId(int length) {
        long nextId = 0;
        long timestamp = this.timeGen();

        if (this.lastTimestamp == timestamp) {
            //与运算
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            nextId = 0L;
        } else {
            this.lastTimestamp = timestamp;
            nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << this.workerIdShift)
                    | (this.sequence);
        }

        String nextIdStr = nextId + "";
        String id = String.format("%08d", nextId).substring(nextIdStr.length() - length, nextIdStr.length());
        return id;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
    public static void main(String[] args) {
        System.err.println(5&6);
    }
}
