package com.pjmike.designpatterns.filter;

/**
 * @author: pjmike
 * @create: 2019/11/28
 */
public interface Filter {
    /**
     * 过滤
     *
     * @param request
     */
    void filter(Request request,FilterChain filterChain);
}
