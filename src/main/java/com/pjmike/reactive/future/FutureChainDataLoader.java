package com.pjmike.reactive.future;

import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/29
 */
public class FutureChainDataLoader extends DataLoader{
    protected void load() {
        //main -> submit -> ..
           //sub-thread -> submit: F1->F2->F3
        CompletableFuture
                .runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                //完成时回调
                .whenComplete((result, throwable) -> {
                    System.out.printf("[线程：“" + Thread.currentThread().getName() + "] 加载完成");
                })
                //等待完成
                .join();//可以去掉，去掉后就直接返回了
    }

    public static void main(String[] args) {
        new FutureChainDataLoader().load();
    }
}
