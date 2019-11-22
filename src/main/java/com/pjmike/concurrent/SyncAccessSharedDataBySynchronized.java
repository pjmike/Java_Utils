package com.pjmike.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @description: 同步访问共享数据：通过synchronized
 * @author: pjmike
 * @create: 2019/11/22
 */
public class SyncAccessSharedDataBySynchronized {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested()) {
                i++;
            }
        }, "thread-1");
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }
}
