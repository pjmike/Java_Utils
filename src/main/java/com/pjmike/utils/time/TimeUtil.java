package com.pjmike.utils.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/18
 */
public class TimeUtil {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurTime() {
        return simpleDateFormatThreadLocal.get().format(new Date());
    }
}
