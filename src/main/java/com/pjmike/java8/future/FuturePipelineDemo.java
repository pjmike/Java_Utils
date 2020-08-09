package com.pjmike.java8.future;

import java.util.concurrent.*;

/**
 * @description: 异步任务存在依赖性，使用 Future 不好做结果编排
 * @author: pjmike
 * @create: 2020/04/04
 */
public class FuturePipelineDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            Future<Order> future = service.submit(getOrderTask());
            Order order = future.get();//blocking

            Future<Order> future1 = service.submit(performPaymentTask(order));
            order = future1.get();//blocking

            Future<Order> future2 = service.submit(dispatchTask(order));
            order = future2.get();//blocking

            Future<Order> future3 = service.submit(sendMessageTask(order));
            order = future3.get(); //blocking
            //TODO
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Callable<Order> sendMessageTask(Order order) {
        return null;
    }

    private static Callable<Order> dispatchTask(Order order) {
        return null;
    }

    private static Callable<Order> performPaymentTask(Order order) {
        return null;
    }

    private static Callable<Order> getOrderTask() {
        return null;
    }
    private static final class Order {

    }

    private static final void completableFuture() {
        CompletableFuture.supplyAsync(() -> getOrder())
                .thenApply(order -> performPayment(order))
                .thenApply(order -> dispatch(order))
                .thenApply(order -> sendMessage(order));

    }

    private static Order sendMessage(Order order) {
        return null;
    }

    private static Order dispatch(Order order) {
        return null;
    }

    private static Order performPayment(Order order) {
        return null;
    }

    private static <Order> FuturePipelineDemo.Order getOrder() {
        return null;
    }
}
