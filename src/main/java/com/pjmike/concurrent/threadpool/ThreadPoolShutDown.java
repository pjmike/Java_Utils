package com.pjmike.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: 优雅的关闭线程池 : 一般需要配合 awaitTermination 方法
 * @author: pjmike
 * @create: 2020/03/22
 */
@Slf4j
public class ThreadPoolShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <100 ; i++) {
            Thread.sleep(100);
            executorService.execute(()-> log.info("{} is running", Thread.currentThread().getName()));
        }
        // 关闭线程池
        executorService.shutdown();

        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            log.info("------------线程池还在运行--------------");
        }
        log.info("------------线程池已关闭--------------");
    }
}
