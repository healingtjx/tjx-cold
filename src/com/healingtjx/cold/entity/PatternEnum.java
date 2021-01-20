package com.healingtjx.cold.entity;

/**
 * @Author: tjx
 * @Description: 模式
 * @Date: 创建于10:26 2021-01-20
 **/
public enum PatternEnum {


    /**
     * 简单
     */
    SIMPLE("简单", "SIMPLE"),

    /**
     * 复杂
     */
    INTRICACY("复杂", "INTRICACY");


    private String name;


    private String key;


    PatternEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }


}
