package com.pjmike.designpatterns.strategy.interfaces;

import com.pjmike.designpatterns.strategy.Order;

/**
 * @author pjmike
 */
public interface DiscountStrategy {
    double calDiscount(Order order);
}
