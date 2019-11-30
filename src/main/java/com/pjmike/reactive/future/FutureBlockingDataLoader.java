package com.pjmike.reactive.future;

import java.util.concurrent.*;

/**
 * @description: Future阻塞数据加载器,future#get阻塞，比串行更长
 * @author: pjmike
 * @create: 2019/11/29
 */
public class FutureBlockingDataLoader extends DataLoader{
    public void load() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        runCompletely(executorService.submit(super::loadConfigurations));
        runCompletely(executorService.submit(super::loadUsers));
        runCompletely(executorService.submit(super::loadOrders));
        executorService.shutdown();
    }

    /**
     * Future#get() 方法不得不等待任务执行完成，
     * 换言之，如果多个任务提交后，返回的多个 Future 逐一调用 get() 方法时，
     * 将会依次 blocking，任务的执行从并行变为串行
     * @param future
     */
    private void runCompletely(Future future) {
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }


}
