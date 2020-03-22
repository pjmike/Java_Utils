package com.pjmike.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 监控线程池状态的示例代码
 * @author: pjmike
 * @create: 2020/03/18
 */
@Slf4j
public class MonitorThreadPoolStats {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        printThreadPoolStats(threadPool);
        for (int i = 0; i <10 ; i++) {
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void printThreadPoolStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(
                        () -> log.info("Thread pool monitors metrics : poolSize:{}, activeThreads:{}, completedTasks:{}, QueueTasks: {}",
                        threadPool.getPoolSize(), threadPool.getActiveCount(), threadPool.getCompletedTaskCount(), threadPool.getQueue().size()),
      0, 1, TimeUnit.SECONDS);
    }
}
