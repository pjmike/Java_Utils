package com.pjmike.memory;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/18
 */
public class FreeMemoryUtil {
    public static String getFreeMemory() {
        long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        return "free=" + free + "M,total=" + total + "M,max=" + max + "M";
    }
}
