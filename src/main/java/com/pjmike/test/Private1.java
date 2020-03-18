package com.pjmike.test;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/02/25
 */
public class Private1 extends PrivateGatewayFilter{
    @Override
    String filterType() {
        return null;
    }

    @Override
    public void filter() {
        System.out.println("private1");
    }
}
