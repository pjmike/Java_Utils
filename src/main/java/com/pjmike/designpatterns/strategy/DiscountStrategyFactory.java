package com.pjmike.designpatterns.strategy;

import com.pjmike.designpatterns.strategy.interfaces.DiscountStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 工厂类
 * @author: pjmike
 * @create: 2020/03/25
 */
public class DiscountStrategyFactory {
    private static Map<OrderType, DiscountStrategy> strategyMap = new HashMap<>();
    // 如果策略类无状态，直接使用Map先进行本地缓存，后面获取策略类直接从Map获取即可
    static {
        strategyMap.put(OrderType.NORMAL, new NormalDiscountStrategy());
        strategyMap.put(OrderType.GROUP, new GroupDiscountStrategy());
        strategyMap.put(OrderType.PROMOTION, new PromotionDiscountStrategy());
    }

    public static DiscountStrategy getDiscountStrategy(OrderType type) {
        return strategyMap.get(type);
    }
}
