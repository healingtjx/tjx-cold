package com.healingtjx.cold.entity;

/**
 * @Author: tjx
 * @Description: 代码块模板选择
 * @Date: 创建于10:54 2021-01-20
 **/
public enum TemplateItemEnum {
    CONTROLLER("controller"), SERVICE("service"), IMPL("impl");

    private String name;

    TemplateItemEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
