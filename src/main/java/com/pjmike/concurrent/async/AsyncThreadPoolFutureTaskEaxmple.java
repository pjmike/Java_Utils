package com.pjmike.concurrent.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/01/25
 */
public class AsyncThreadPoolFutureTaskEaxmple {
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
        long start = System.currentTimeMillis();
        //创建Future任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            String result = null;
            try {
                result = doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        //开启异步单元执行任务
        THEAD_POOL_EXECUTOR.execute(futureTask);
        System.out.println(futureTask.get());
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
        //关闭线程池
        THEAD_POOL_EXECUTOR.shutdown();
    }
}
