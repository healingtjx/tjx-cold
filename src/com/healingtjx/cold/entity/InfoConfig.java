package com.healingtjx.cold.entity;

import java.io.Serializable;

/**
 * @Author: tjx
 * @Description: 基础信息配置
 * @Date: 创建于10:41 2021-01-18
 **/
public class InfoConfig implements Serializable {


    /**
     * 控制层包配置
     */
    private String controllerPackage;

    /**
     * 业务层包配置
     */
    private String servicePackage;

    /**
     * 业务层实现类包配置
     */
    private String implPackage;

    /**
     * 生成模式配置 0 默认 1 模式1  2模式2
     */
    private int pattern;

    public InfoConfig() {
        super();
    }

    public InfoConfig(String controllerPackage, String servicePackage, String implPackage, int pattern) {
        this.controllerPackage = controllerPackage;
        this.servicePackage = servicePackage;
        this.implPackage = implPackage;
        this.pattern = pattern;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getImplPackage() {
        return implPackage;
    }

    public void setImplPackage(String implPackage) {
        this.implPackage = implPackage;
    }

    public int getPattern() {
        return pattern;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }
}
