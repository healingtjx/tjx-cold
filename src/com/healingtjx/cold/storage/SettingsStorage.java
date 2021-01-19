package com.healingtjx.cold.storage;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.TemplateConfig;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
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

    /**
     * 简单模式
     */
    private String SIMPLE_KEY = "SIMPLE";
    /**
     * 复杂模式
     */
    private String INTRICACY_KEY = "INTRICACY";

    @Nullable
    @Override
    public SettingsStorage getState() {

        if (infoConfig == null) {
            loadDefaultSettings();
        }
        return this;
    }

    /**
     * 加载默认数据
     */
    private void loadDefaultSettings() {
        //基础信息配置
        InfoConfig infoConfig = new InfoConfig("controller", "service", "service.impl", 0);
        //基础模板配置
        templateConfigList = new HashMap<>(4);
        TemplateConfig simpleTemplate = new TemplateConfig("controller", "service", "impl");
        TemplateConfig intricacyTemplate = new TemplateConfig("intricacyController", "intricacyService", "intricacyImpl");
        templateConfigList.put(SIMPLE_KEY, simpleTemplate);
        templateConfigList.put(INTRICACY_KEY, intricacyTemplate);
        this.infoConfig = infoConfig;
    }


    @Override
    public void loadState(@NotNull SettingsStorage settingsStorage) {
        XmlSerializerUtil.copyBean(settingsStorage, this);
    }

    public void setInfoConfig(InfoConfig infoConfig) {
        this.infoConfig = infoConfig;
    }

    public InfoConfig getInfoConfig() {
        return infoConfig;
    }


}
