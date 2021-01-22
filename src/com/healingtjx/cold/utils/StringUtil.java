package com.healingtjx.cold.utils;

/**
 * @Author: tjx
 * @Description: 字符串工具类
 * @Date: 创建于17:32 2021-01-22
 **/
public class StringUtil {

    /**
     * 首字母转小写
     *
     * @param s 字符串
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
