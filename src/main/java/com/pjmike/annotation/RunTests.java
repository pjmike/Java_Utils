package com.pjmike.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/15
 */
public class RunTests {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int tests = 0;
        int passed = 0;
        Class<?> aClass = Class.forName("com.pjmike.annotation.Sample");
        Sample sample = (Sample) aClass.newInstance();
        for (Method m : aClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(sample);
                    passed++;
                } catch (IllegalAccessException e) {
                    System.out.println("invalid @Test: " + m);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    System.out.println(m + " failed: " + cause);
                }
            }
        }
        System.out.printf("Passed: %d,failed: %d\n", passed, tests - passed);
    }
}
