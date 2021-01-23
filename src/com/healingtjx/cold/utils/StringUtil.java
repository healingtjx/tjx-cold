package com.healingtjx.cold.utils;

/**
 * @Author: tjx
 * @Description: 字符串工具类
 * @Date: 创建于17:32 2021-01-22
 **/
public class StringUtil {

    /**
     * 代码src 目录
     */
    private static final String PACKAGE_SRC = "src/main/java";

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

    /**
     * 根据路径生成 package
     *
     * @param src 路径
     * @return
     */
    public static String getPackageBySrc(String src) {
        //定位到 src/main/java
        int index = src.lastIndexOf(PACKAGE_SRC);
        int startIndex = index + PACKAGE_SRC.length() + 1;
        String packageSrc = src.substring(startIndex);
        return packageSrc.length() > 0 ? packageSrc.replaceAll("/", ".") : null;
    }

    public static void main(String[] args) {
        System.out.println(getPackageBySrc("C:/work/zhongyitang-vehicle-service/test/src/main/java/com/test/tjx/test"));

    }
}
