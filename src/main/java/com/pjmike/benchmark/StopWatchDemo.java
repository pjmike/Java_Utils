package com.pjmike.benchmark;


import org.springframework.util.StopWatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.LongStream;

public class StopWatchDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("JobTest");
        LongStream.rangeClosed(1,10000)
                .parallel()
                .forEach(i->{
                    String key = "Item" + ThreadLocalRandom.current().nextInt(100);
                    map.computeIfAbsent(key, k -> new LongAdder()).increment();
                });
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        //StopWatch '': running time = 181793300 ns
        //---------------------------------------------
        //ns         %     Task name
        //---------------------------------------------
        //181793300  100%  JobTest
    }
}
