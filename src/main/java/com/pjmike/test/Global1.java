package com.pjmike.test;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/02/25
 */
public class Global1 extends GlobalGatewayFilter{
    @Override
    String filterType() {
        return null;
    }

    @Override
    public void filter() {
        System.out.println("global1");
    }
}
