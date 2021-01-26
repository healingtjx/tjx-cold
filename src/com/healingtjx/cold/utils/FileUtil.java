package com.healingtjx.cold.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author: tjx
 * @Description: 文件相关操作类
 * @Date: 创建于15:47 2021-01-22
 **/
public class FileUtil {


    /**
     * 根据路径读取内容
     *
     * @param src 路径
     * @return 内容
     */
    public static String readBySrc(String src) {
        InputStream in = FileUtil.class.getResourceAsStream(src);
        byte[] b = new byte[1024 * 4];
        int len = 0;
        int temp;
        try {
            while ((temp = in.read()) != -1) {
                b[len] = (byte) temp;
                len++;
            }
            in.close();
            return new String(b, 0, len, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 不存在就创建
     *
     * @param src 路径
     */
    public static void mkdirBySrc(String src) {
        File file = new File(src);
        //如果文件夹不存在
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
    }
}
