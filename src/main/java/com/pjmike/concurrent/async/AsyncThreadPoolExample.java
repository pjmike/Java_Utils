package com.pjmike.concurrent.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @description: 使用线程池+Callable方式进行异步编程
 * @author: pjmike
 * @create: 2020/01/24
 */
public class AsyncThreadPoolExample {
    public static String doSomething() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "A Task Done";
    }

    /**
     * 设置线程池核心线程个数为当前物理机的CPU核数
     */
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor THEAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(5),
            new ThreadFactoryBuilder().setNameFormat("ASYNC_POOL_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //开启异步执行任务
        Future<String> result = THEAD_POOL_EXECUTOR.submit(() -> doSomething());
        //同步等待执行结果
        //存在的问题: 调用get()方法获取结果时还是同步阻塞的
        System.out.println(result.get());
    }
}
