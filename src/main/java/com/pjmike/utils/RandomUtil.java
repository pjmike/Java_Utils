package com.pjmike.utils;


import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 随机数生成器
 * @author: pjmike
 * @create: 2019/11/19
 */
public class RandomUtil {
    /**
     * 使用标准库里的工具，比如common-lang3中的RandomUtils
     *
     * @param start
     * @param end
     * @return
     */
    public static int getRandomInt(int start, int end) {
        return RandomUtils.nextInt(start, end);
    }

    /**
     * 获取随机数生成器对象<br>
     * ThreadLocalRandom是JDK 7 之后提供并发产生随机数，能够解决多个线程发生的竞争争夺
     *
     * @return {@link ThreadLocalRandom}
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }
    public static void main(String[] args) {
        Random random = new Random();
        //计算int范围内的随机数
        System.out.println(random.nextInt());
        //求出[0,10)之间int类型的随机数
        System.out.println(random.nextInt(10));

        //高性能的ThreadLocalRandom
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom.nextInt(10));

        //使用标准库中
        System.out.println(getRandomInt(0,10));
    }

}
