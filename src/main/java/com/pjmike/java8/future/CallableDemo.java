package com.pjmike.java8.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/04/04
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(10);
            }
        });
        try {
            Integer result = future.get();
            System.out.println("Result from the task is: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
