package com.pjmike.method;

import java.util.Date;

/**
 * <p>
 * 必要时进行保护性拷贝
 * </p>
 *
 * <p>
 * 对于不可变类进行保护性拷贝，比如String中的做法：
 *    public String(char value[]) {
 *         this.value = Arrays.copyOf(value, value.length);
 *    }
 * </p>
 *
 * <p>
 * Period类封装一个可以表示不可变的时间周期，使用了保护性拷贝，
 * 因为需要额外创建对象，存在一定的性能问题，必要时使用
 * </p>
 * @author: pjmike
 * @create: 2019/11/17
 */
public final class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(start.getTime());
        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
