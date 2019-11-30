package com.pjmike.reactive.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Flux测试
 *
 * @author: pjmike
 * @create: 2019/11/30
 */
public class FluxDemo {
    public static void main(String[] args) {
        println("运行...");
        //发布A->B->C数据流
        Flux.just("A", "B", "C")
//                .publishOn(Schedulers.elastic())//切换线程池
                .subscribe(
                        //数据消费=Subscriber#onNext(T)
                        FluxDemo::println,
                        //异常处理=OnError(Throwable)
                        FluxDemo::println,
                        //完成回调=onComplete()
                        ()-> println("完成操作"),
                        //背压操作  onSubscribe(Subscription)
                        subscription -> {
                            subscription.request(2);//请求上游元素的数量为2
                            subscription.cancel();//取消上游传输数据到下游
                        }
                );
    }

    public static void println(Object object) {
        String name = Thread.currentThread().getName();
        System.out.println("[线程：" + name + "] " + object);
    }
}
