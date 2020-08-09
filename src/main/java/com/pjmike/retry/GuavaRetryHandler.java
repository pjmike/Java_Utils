package com.pjmike.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaRetryHandler {
    private final static Retryer<Object> retryer = RetryerBuilder.<Object>newBuilder()
            .retryIfResult(Predicates.isNull())
            .retryIfExceptionOfType(IOException.class)
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
            .build();
    private static RetryService retryService = new RetryServiceImpl();
    public static void main(String[] args) {
        //需要重试的方法
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return retryService.run();
            }
        };
        //添加重试方法到执行器
        try {
            Object call = retryer.call(callable);
            System.out.println("result: " + call);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }
}
