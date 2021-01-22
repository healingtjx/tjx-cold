package com.healingtjx.cold.service.impl;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.PatternEnum;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.entity.TemplateItemEnum;
import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.utils.FileUtil;
import com.healingtjx.cold.utils.StringUtil;
import com.healingtjx.cold.utils.VmUtil;
import com.intellij.openapi.components.ServiceManager;
import org.apache.velocity.VelocityContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @Author: tjx
 * @Description: 生成代码serviceImpl
 * @Date: 创建于16:46 2021-01-15
 **/
public class GenerateServiceImpl implements GenerateService {

    private SettingsStorage settingsStorage;

    private DateTimeFormatter fmTime = DateTimeFormatter.ofPattern("HH:mm YYYY-MM-dd ");

    public GenerateServiceImpl() {
        if (settingsStorage == null) {
            settingsStorage = ServiceManager.getService(SettingsStorage.class);
        }
    }


    @Override
    public void createTemplateCode(String filePath, String name) {
        //加载配置
        InfoConfig infoConfig = settingsStorage.getInfoConfig();

        //生成文件夹
        String controllerFileSrc = filePath + "/" + infoConfig.getControllerPackage();
        String serviceFileSrc = filePath + "/" + infoConfig.getServicePackage();
        String implFileSrc = filePath + "/" + infoConfig.getImplPackage();
        //不存在就创建
        FileUtil.mkdirBySrc(controllerFileSrc);
        FileUtil.mkdirBySrc(serviceFileSrc);
        FileUtil.mkdirBySrc(implFileSrc);

        //获取时间
        String time = LocalDateTime.now().format(fmTime);

        //获取模板
        Map<String, TemplateConfig> templateConfigList = settingsStorage.getTemplateConfigList();
        TemplateConfig templateConfig = templateConfigList.get(PatternEnum.SIMPLE.getKey());

        //生成controller
        VelocityContext controllerContext = new VelocityContext();
        controllerContext.put("package", controllerFileSrc);
        controllerContext.put("time", time);
        controllerContext.put("fileName", name + "Controller");
        controllerContext.put("serviceFileName", name + "Service");
        controllerContext.put("serviceFileName", StringUtil.toLowerCaseFirstOne(name + "Service"));
        String controllerOut = controllerFileSrc + "/" + name + "Controller";
        VmUtil.create(controllerContext, templateConfig.getControllerTemplate(), controllerOut);

    }
}
