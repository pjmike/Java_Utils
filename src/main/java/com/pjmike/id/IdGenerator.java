package com.pjmike.id;

/**
 * ID 生成器，面向接口编程，而不是实现
 *
 * 代码来源：https://time.geekbang.org/column/article/191621
 */
public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
