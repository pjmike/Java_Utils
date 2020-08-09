package com.pjmike.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryServiceImpl implements RetryService{
    public static final Integer MAX_TIMES = 5;
    private Logger logger = LoggerFactory.getLogger(RetryServiceImpl.class);
    @Override
    public void query() {
        int times = 0;
        while (times < MAX_TIMES) {
            try {
                //Do Something
                return;
            } catch (Exception e) {
                times++;
            }
        }
        throw new RuntimeException("do something failed after retry "+ MAX_TIMES + "times");
    }

    @Override
    public Object run() {
        System.out.println("开始执行");
        return new Object();
    }

    @Override
    public Object retry() throws Exception {
        int retry = 0;
        for (; retry < MAX_TIMES; retry++) {
            try {
                //DO something
                //成功即return
                return null;
            } catch (Exception e) {
                //失败重试
                logger.warn("init something exception, retrying ...",e);
            }
        }
        logger.error("init something failed,abort retrying and throw Exception..");
        throw new Exception("init something failed after " + MAX_TIMES + " times retry.");
    }

}

