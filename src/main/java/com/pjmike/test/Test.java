package com.pjmike.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/02/25
 */
public class Test {
    public static void main(String[] args) {
        List<GatewayFilter> filters = new ArrayList<>();
        filters.add(new Global1());
        filters.add(new Private1());
        for (GatewayFilter filter : filters) {
            filter.filter();
        }
    }
}
