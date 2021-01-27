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
     * class 字符串
     */
    private static final String CLASS_STR = "class";

    /**
     * interface 字符串
     */
    private static final String INTERFACE_STR = "interface";

    /**
     * implements
     */
    private static final String IMPLEMENTS_STR = "implements";


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
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
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

    /**
     * 根据 代码获取类名
     *
     * @param str 代码
     * @return
     */
    public static String getClassNameByStr(String str) {
        //定位到 class
        int classIndnx = str.indexOf(CLASS_STR);
        int interfaceIndnx = str.indexOf(INTERFACE_STR);
        if (classIndnx == -1 && interfaceIndnx == -1) {
            return null;
        }
        if (classIndnx != -1) {
            str = str.substring(classIndnx + CLASS_STR.length());
        }else{
            str = str.substring(interfaceIndnx + INTERFACE_STR.length());
        }
        //定位到括号
        int bracketIndex = str.indexOf('{');
        str = str.substring(0, bracketIndex);
        //判断是否有 implements
        int implementsIndex = str.indexOf(IMPLEMENTS_STR);
        if (implementsIndex != -1) {
            str = str.substring(0, implementsIndex);
        }
        // 去除前后空格
        return str.trim();
    }

    public static void main(String[] args) {
        String str =
                "package com.healing.tjx.shop.controller;\n" +
                        "\n" +
                        "import io.swagger.annotations.Api;\n" +
                        "import lombok.extern.slf4j.Slf4j;\n" +
                        "import com.healing.tjx.shop.service.TestService;\n" +
                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                        "import org.springframework.web.bind.annotation.RestController;\n" +
                        "\n" +
                        "/**\n" +
                        " * @Author: tjx\n" +
                        " * @Description: 描述\n" +
                        " * @Date: 创建于17:11 2021-01-27 \n" +
                        " **/\n" +
                        "@Slf4j\n" +
                        "@RestController\n" +
                        "@RequestMapping(\"/TestController\")\n" +
                        "@Api(tags = \"TestController\", value = \"描述\")\n" +
                        "public class TestController implements GenerateService {\n" +
                        "\n" +
                        "    @Autowired\n" +
                        "    private TestService testService;\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "}\n" +
                        "\n" +
                        "package com.healing.tjx.shop.service;\n" +
                        "\n" +
                        "/**\n" +
                        "* @Author: tjx\n" +
                        "* @Description: 描述\n" +
                        "* @Date: 创建于17:11 2021-01-27 \n" +
                        "**/\n" +
                        "public interface TestService {\n" +
                        "}\n" +
                        "\n" +
                        "package com.healing.tjx.shop.service.impl;\n" +
                        "\n" +
                        "import com.healing.tjx.shop.service.TestService;\n" +
                        "import lombok.extern.slf4j.Slf4j;\n" +
                        "import org.springframework.stereotype.Service;\n" +
                        "\n" +
                        "/**\n" +
                        " * @Author: tjx\n" +
                        " * @Description: 描述\n" +
                        " * @Date: 创建于17:11 2021-01-27 \n" +
                        " **/\n" +
                        "@Slf4j\n" +
                        "@Service\n" +
                        "public class TestServiceImpl implements TestService {\n" +
                        "}\n" +
                        "\n";
        System.out.println(getClassNameByStr(str));
    }
}
