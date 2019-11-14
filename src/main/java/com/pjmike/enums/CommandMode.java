package com.pjmike.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/14
 */
@Getter
public enum  CommandMode {
    /**
     * login
     */
    LOGIN(1,"login"),
    /**
     * logout
     */
    logout(2, "logout");

    private int mode;
    private String text;

    CommandMode(int mode, String text) {
        this.mode = mode;
        this.text = text;
    }

    public static CommandMode getEnum(final Integer mode) {
        return mode == null ? null : Arrays.stream(values())
                .filter(t -> t.getMode() == mode)
                .findFirst()
                .orElse(null);
    }
}
