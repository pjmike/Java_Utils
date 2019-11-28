package com.pjmike.filter;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/28
 */
public class FilterA implements Filter {
    @Override
    public void filter(Request request, FilterChain filterChain) {
        //do something
        filterChain.doFilter(request);
    }
}
