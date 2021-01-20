package com.healingtjx.cold.entity;

import java.io.Serializable;

/**
 * @Author: tjx
 * @Description: 单选框对象
 * @Date: 创建于10:36 2021-01-20
 **/
public class SelectItem implements Serializable {

    private String name;

    private String key;

    public SelectItem(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return name;
    }
}
