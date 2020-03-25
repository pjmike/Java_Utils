package com.pjmike.designpatterns.strategy;

import com.pjmike.designpatterns.strategy.interfaces.DiscountStrategy;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/25
 */
public class OrderService {
    public double discount(Order order) {
        OrderType type = order.getType();
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(type);
        return discountStrategy.calDiscount(order);
    }
}
