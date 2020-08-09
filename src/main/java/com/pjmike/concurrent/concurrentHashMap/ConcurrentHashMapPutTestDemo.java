package com.pjmike.concurrent.concurrentHashMap;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ConcurrentHashMapPutTestDemo {
    /**
     * 使用LongStream初始化count大小的Map，例子：
     * key: 49efd6b3-3a65-47ad-869e-60ceb5da2a1d, value: 140
     * key: d16269b6-eceb-483e-88c8-daaaac2123eb, value: 577
     * @param count
     * @return
     */
    private static ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, Long> data = getData(900);
        data.forEach((key, value) -> System.out.println("key: " + key + ", value: " + value));
    }
}
