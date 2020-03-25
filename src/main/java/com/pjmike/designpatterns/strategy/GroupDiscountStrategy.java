package com.pjmike.designpatterns.strategy;

import com.pjmike.designpatterns.strategy.interfaces.DiscountStrategy;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/25
 */
public class GroupDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(Order order) {
        return 0;
    }
}
