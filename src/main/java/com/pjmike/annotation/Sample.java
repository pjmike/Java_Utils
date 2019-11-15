package com.pjmike.annotation;

/**
 * @description: 注解测试类
 * @author: pjmike
 * @create: 2019/11/15
 */
public class Sample {
    @Test
    public static void m1(){}
    public static void m2(){}
    @Test
    public static void m3(){
        throw new RuntimeException();
    }
    public static void m4(){}
    @Test
    public void m5(){}
    @Test
    public static void m6(){}
}
