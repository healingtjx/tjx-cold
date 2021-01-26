package com.healingtjx.cold.service.impl;

import com.google.gson.Gson;
import com.healingtjx.cold.entity.GenerationStrategyEnum;
import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.ModelEnum;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.utils.FileUtil;
import com.healingtjx.cold.utils.StringUtil;
import com.healingtjx.cold.utils.VmUtil;
import org.apache.velocity.VelocityContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tjx
 * @Description: 生成代码serviceImpl
 * @Date: 创建于16:46 2021-01-15
 **/
public class GenerateServiceImpl implements GenerateService {

    private DateTimeFormatter fmTime = DateTimeFormatter.ofPattern("HH:mm YYYY-MM-dd ");

    public GenerateServiceImpl() {
    }

    /**
     * 根据 model 生成路径
     *
     * @param filePath
     * @param infoConfig
     * @param packageName
     * @param model
     * @return
     */
    private Map<String, String> getSrcByModel(String filePath, InfoConfig infoConfig, String packageName, int model) {
        Map<String, String> srcMap = new HashMap<>(3);
        //默认情况
        if (ModelEnum.DEFAULT.getModel() == model) {
            srcMap.put("controllerFileSrc", filePath + "/" + infoConfig.getControllerPackage());
            srcMap.put("serviceFileSrc", filePath + "/" + infoConfig.getServicePackage());
            srcMap.put("implFileSrc", filePath + "/" + infoConfig.getImplPackage());
        }
        //模式1 都需要 加包名
        if (ModelEnum.MODEL_ONE.getModel() == model) {
            srcMap.put("controllerFileSrc", filePath + "/" + infoConfig.getControllerPackage() + "/" + packageName);
            srcMap.put("serviceFileSrc", filePath + "/" + infoConfig.getServicePackage() + "/" + packageName);
            srcMap.put("implFileSrc", filePath + "/" + infoConfig.getImplPackage() + "/" + packageName);
        }
        //模式2 只有contrller 要加包名
        if (ModelEnum.MODEL_TWO.getModel() == model) {
            srcMap.put("controllerFileSrc", filePath + "/" + infoConfig.getControllerPackage() + "/" + packageName);
            srcMap.put("serviceFileSrc", filePath + "/" + infoConfig.getServicePackage());
            srcMap.put("implFileSrc", filePath + "/" + infoConfig.getImplPackage());
        }
        //模式3 只有 service 要加包名
        if (ModelEnum.MODEL_THREE.getModel() == model) {
            srcMap.put("controllerFileSrc", filePath + "/" + infoConfig.getControllerPackage());
            srcMap.put("serviceFileSrc", filePath + "/" + infoConfig.getServicePackage() + "/" + packageName);
            srcMap.put("implFileSrc", filePath + "/" + infoConfig.getImplPackage() + "/" + packageName);
        }


        return srcMap;
    }

    @Override
    public void createTemplateCode(String filePath, String name, String packageName, String patternKey, int model, int generationStrategy, SettingsStorage settingsStorage) {
        //加载配置
        InfoConfig infoConfig = settingsStorage.getInfoConfig();
        //获取src
        Map<String, String> srcByModel = getSrcByModel(filePath, infoConfig, packageName, model);


        //生成文件夹
        String controllerFileSrc = srcByModel.get("controllerFileSrc");
        String serviceFileSrc = srcByModel.get("serviceFileSrc");
        String implFileSrc = srcByModel.get("implFileSrc");


        //获取时间
        String time = LocalDateTime.now().format(fmTime);

        //获取模板
        Map<String, TemplateConfig> templateConfigList = settingsStorage.getTemplateConfigList();
        TemplateConfig templateConfig = templateConfigList.get(patternKey);

        //策略判断
        if (GenerationStrategyEnum.ALL.getStrategy() == generationStrategy || GenerationStrategyEnum.ONLY_CONTROLLER.getStrategy() == generationStrategy) {
            //不存在就创建
            FileUtil.mkdirBySrc(controllerFileSrc);
            //生成controller
            VelocityContext controllerContext = new VelocityContext();
            controllerContext.put("servicePackage", StringUtil.getPackageBySrc(serviceFileSrc));
            controllerContext.put("serviceFileName", name + "Service");
            controllerContext.put("package", StringUtil.getPackageBySrc(controllerFileSrc));
            controllerContext.put("time", time);
            controllerContext.put("fileName", name + "Controller");
            controllerContext.put("serviceFileName", name + "Service");
            controllerContext.put("serviceName", StringUtil.toLowerCaseFirstOne(name + "Service"));
            String controllerOut = controllerFileSrc + "/" + name + "Controller.java";
            VmUtil.create(controllerContext, templateConfig.getControllerTemplate(), controllerOut);
        }
        //策略判断
        if (GenerationStrategyEnum.ALL.getStrategy() == generationStrategy || GenerationStrategyEnum.ONLY_SERVICE.getStrategy() == generationStrategy) {
            //不存在就创建
            FileUtil.mkdirBySrc(serviceFileSrc);
            FileUtil.mkdirBySrc(implFileSrc);
            //生成service
            VelocityContext serviceContext = new VelocityContext();
            serviceContext.put("package", StringUtil.getPackageBySrc(serviceFileSrc));
            serviceContext.put("time", time);
            serviceContext.put("fileName", name + "Service");
            String serviceOut = serviceFileSrc + "/" + name + "Service.java";
            VmUtil.create(serviceContext, templateConfig.getServiceTemplate(), serviceOut);

            //生成impl
            VelocityContext implContext = new VelocityContext();
            implContext.put("servicePackage", StringUtil.getPackageBySrc(serviceFileSrc));
            implContext.put("serviceFileName", name + "Service");
            implContext.put("package", StringUtil.getPackageBySrc(implFileSrc));
            implContext.put("time", time);
            implContext.put("fileName", name + "ServiceImpl");
            String implOut = implFileSrc + "/" + name + "ServiceImpl.java";
            VmUtil.create(implContext, templateConfig.getImplTemplate(), implOut);
        }
    }


}
