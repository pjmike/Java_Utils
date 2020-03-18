package com.pjmike.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/08
 */
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {

            executorService.execute(()->{
                try {
                    Integer before = threadLocal.get();
                    threadLocal.set(before + 1);
                    Integer after = threadLocal.get();
                    System.out.println("before: " + before + ",after: " + after);
                } finally {
                    threadLocal.remove();
                }
            });
        }
        executorService.shutdown();
    }
}
