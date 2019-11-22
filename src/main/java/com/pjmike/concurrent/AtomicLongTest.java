package com.pjmike.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/22
 */
public class AtomicLongTest {
    private static final AtomicLong nextNum = new AtomicLong();

    public static long generateNum() {
        return nextNum.getAndIncrement();
    }
}
