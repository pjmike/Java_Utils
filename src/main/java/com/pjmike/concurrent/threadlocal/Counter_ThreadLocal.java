package com.pjmike.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/06
 */
public class Counter_ThreadLocal {
    private ThreadLocal<AtomicLong> threadLocal = new ThreadLocal<>();

    public long incrementAndGet() {
        return threadLocal.get().incrementAndGet();
    }
}
