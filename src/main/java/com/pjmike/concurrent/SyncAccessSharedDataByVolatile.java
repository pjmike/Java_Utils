package com.pjmike.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @description: 同步访问共享数据：通过volatile关键字的方式
 * @author: pjmike
 * @create: 2019/11/22
 */
public class SyncAccessSharedDataByVolatile {
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        long end = System.currentTimeMillis();
        //差不多等待1s
        System.out.println("耗时：" + (end - start) + "ms");
    }
}
