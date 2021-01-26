package com.healingtjx.cold.storage;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.PatternEnum;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.utils.FileUtil;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于17:10 2021-01-15
 **/
@State(name = "SettingsStorage", storages = {@Storage("$APP_CONFIG$/tjxCold-settings.xml")})
public class SettingsStorage implements PersistentStateComponent<SettingsStorage> {

    /**
     * 存储配置数据
     */
    private InfoConfig infoConfig;

    /**
     * 二维数组 保存 template 模板
     * [[简单配置],[复杂配置]]
     */
    private Map<String, TemplateConfig> templateConfigList;


    @Nullable
    @Override
    public SettingsStorage getState() {
        System.out.println("123123123");
        if (infoConfig == null) {
            loadDefaultSettings();
        }
        return this;
    }

    /**
     * 加载默认数据
     */
    public void loadDefaultSettings() {
        //读取文件里面配置

        //基础信息配置
        InfoConfig infoConfig = new InfoConfig("controller", "service", "service/impl", 0);
        //基础模板配置
        templateConfigList = new HashMap<>(8);

        TemplateConfig simpleTemplate = new TemplateConfig(FileUtil.readBySrc("/template/controller.vm")
                , FileUtil.readBySrc("/template/service.vm")
                , FileUtil.readBySrc("/template/impl.vm")
        );
        TemplateConfig intricacyTemplate = new TemplateConfig(FileUtil.readBySrc("/template/intricacyController.vm")
                , FileUtil.readBySrc("/template/intricacyService.vm")
                , FileUtil.readBySrc("/template/intricacyImpl.vm")
        );
        templateConfigList.put(PatternEnum.SIMPLE.getKey(), simpleTemplate);
        templateConfigList.put(PatternEnum.INTRICACY.getKey(), intricacyTemplate);
        this.infoConfig = infoConfig;
    }


    @Override
    public void loadState(SettingsStorage settingsStorage) {
        XmlSerializerUtil.copyBean(settingsStorage, this);
    }

    public void setInfoConfig(InfoConfig infoConfig) {
        this.infoConfig = infoConfig;
    }


    public InfoConfig getInfoConfig() {
        return infoConfig;
    }


    public Map<String, TemplateConfig> getTemplateConfigList() {
        return templateConfigList;
    }

    public void setTemplateConfigList(Map<String, TemplateConfig> templateConfigList) {
        this.templateConfigList = templateConfigList;
    }
}
