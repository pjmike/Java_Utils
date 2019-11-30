package com.pjmike.reactive.future;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/29
 */
public class DataLoader {
    protected final void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers()", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 :  %d 毫秒\n",
                    Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
