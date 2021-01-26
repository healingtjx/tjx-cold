package com.healingtjx.cold.entity;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于9:53 2021-01-26
 **/
public enum ModelEnum {
    /**
     * 默认模式
     */
    DEFAULT(0),
    /**
     * 模式1
     * controller,service,都有包名
     */
    MODEL_ONE(1),
    /**
     * 模式2
     * controller 有包名
     */
    MODEL_TWO(2),
    /**
     * 模式3
     * service   有包名
     */
    MODEL_THREE(3),;


    private int model;

    ModelEnum(int model) {
        this.model = model;
    }

    public int getModel() {
        return model;
    }
}
