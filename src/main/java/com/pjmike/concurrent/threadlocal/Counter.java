package com.pjmike.concurrent.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
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
