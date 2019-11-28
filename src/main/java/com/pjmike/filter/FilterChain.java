package com.pjmike.filter;

import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/28
 */
public class FilterChain {
    private int index = 0;
    private final List<Filter> filters;

    public FilterChain(List<Filter> filters) {
        this.filters = filters;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void doFilter(Request request) {
        if (this.index < filters.size()) {
            Filter filter = filters.get(index++);
            filter.filter(request, this);
        }
    }
}
