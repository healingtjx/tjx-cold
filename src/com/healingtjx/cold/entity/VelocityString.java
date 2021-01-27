package com.healingtjx.cold.entity;

/**
 * @Author: tjx
 * @Description: 用于 模板生成 定义一些全局方法
 * @Date: 创建于20:15 2021-01-27
 **/
public class VelocityString {

    /**
     * 字符串
     */
    private String str;

    public VelocityString(String str) {
        this.str = str;
    }

    /**
     * 首字符小写
     *
     * @return
     */
    public String toLowerCaseFirstOne() {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }


    @Override
    public String toString() {
        return str;
    }
}
