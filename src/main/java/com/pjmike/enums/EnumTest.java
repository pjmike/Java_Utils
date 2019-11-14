package com.pjmike.enums;

/**
 * @description:  定义一个抽象方法，与每个枚举常量关联起来
 * @author: pjmike
 * @create: 2019/11/14
 */
public enum  EnumTest {
    /**
     * red
     */
    RED {
        @Override
        public String colorName() {
            return "红色";
        }
    },
    /**
     * yellow
     */
    YELLOW {
        @Override
        public String colorName() {
            return "红色";
        }
    },
    /**
     * blue
     */
    BLUE {
        @Override
        public String colorName() {
            return "蓝色";
        }
    };
    public abstract String colorName();
}
