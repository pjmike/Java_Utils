package com.pjmike.concurrent.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 在Tomcat环境下使用ThreadLocal要注意，Tomcat线程池会重用固定的几个线程，一旦线程重用，那么很可能
 * 首次从ThreadLocal获取的值是之前其他用户的请求遗留的值
 *
 * 一般情况下，在一个比较长的请求链路中，我们会使用ThreadLocal中保存变量，以便透传到后续流程
 * 
 * @description: 使用ThreadLocal定义一个计数器
 * @author: pjmike
 * @create: 2020/03/05
 */
public class Counter {
    private AtomicLong counter = new AtomicLong();

    private static Map<Long, Counter> counterMap = new ConcurrentHashMap<>();

    public static Counter getCounter() {
        long id = Thread.currentThread().getId();
        counterMap.putIfAbsent(id, new Counter());
        return counterMap.get(id);
    }

    public long incrementAndGet() {
        return counter.incrementAndGet();
    }
}
