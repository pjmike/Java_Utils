package com.pjmike.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/06
 */
public class ThreadLocalSimpleDateFormat {
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMss HHmm"));

    public String formatDate(Date date) {
        return formatter.get().format(date);
    }
}
