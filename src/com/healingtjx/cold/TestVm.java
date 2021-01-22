package com.healingtjx.cold;

import com.healingtjx.cold.utils.FileUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:05 2021-01-22
 **/
public class TestVm {

    public static void main(String[] args) throws IOException {


        System.out.println(FileUtil.readBySrc("/template/controller.vm"));


        int i = 1;
        if (i == 1) {
            return;
        }

        String content = "package ${package}.controller;\n" +
                "\n" +
                "import io.swagger.annotations.Api;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "/**\n" +
                "* @Author: tjx\n" +
                "* @Description: 描述\n" +
                "* @Date: 创建于10:35 2021-01-22\n" +
                "**/\n" +
                "@Slf4j\n" +
                "@RestController\n" +
                "@RequestMapping(\"/${fileName}\")\n" +
                "@Api(tags = \"${fileName}\", value = \"描述\")\n" +
                "public class ${fileName} {\n" +
                "\n" +
                "\n" +
                "}\n";

        VelocityContext context = new VelocityContext();
        //替换文本
        context.put("package", "com.healing.tjx.admin");
        context.put("fileName", "UmsTestController");
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        //输出路径
        String outPath = "D:\\my_work\\template";

        try {
            StringWriter writer = new StringWriter();
            // 关键方法
            ve.evaluate(context, writer, "", content);
            File file = new File(outPath);
            file.mkdirs();
            //解决乱码
            String fileName = outPath + "\\Test.java";
            System.out.println(fileName);

            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            PrintWriter pw = new PrintWriter(fw);
            pw.println(writer.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
