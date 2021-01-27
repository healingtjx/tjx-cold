package com.healingtjx.cold.utils;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.VelocityString;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;

/**
 * @Author: tjx
 * @Description: 模板生成工具类
 * @Date: 创建于17:15 2021-01-22
 **/
public class VmUtil {


    /**
     * 生成文件
     *
     * @param context  模板参数
     * @param template 模板文件
     * @param out      生成文件路径
     */
    public static void create(VelocityContext context, String template, String out) {
        //实例化
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        try {
            StringWriter writer = new StringWriter();
            //生成代码
            ve.evaluate(context, writer, "", template);
            //获取java类名
            String className = StringUtil.getClassNameByStr(writer.toString());
            if (StringUtil.isNull(className)) {
                return;
            }
            out += className + ".java";

            System.out.println(writer);
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));
            PrintWriter pw = new PrintWriter(fw);
            pw.println(writer.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String template = "package ${test.toLowerCaseFirstOne()};";
        VelocityContext context = new VelocityContext();
        context.put("test", new VelocityString("1estJava"));
        StringWriter writer = new StringWriter();
        //实例化
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        //生成代码
        ve.evaluate(context, writer, "", template);
        System.out.println(writer);
    }
}
