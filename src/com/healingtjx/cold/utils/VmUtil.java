package com.healingtjx.cold.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;

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
        //关闭日志
        ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new NullLogChute());
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

            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));
            PrintWriter pw = new PrintWriter(fw);
            pw.println(writer.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
