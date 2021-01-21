package com.healingtjx.cold.ui;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.storage.SettingsStorage;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:20 2021-01-13
 **/
public class ColdConfigurable implements SearchableConfigurable, Configurable.NoScroll {


    /**
     * 配置界面
     */
    private ColdConfigurationForm configurationForm;

    /**
     * 配置信息
     */
    private SettingsStorage settingsStorage;

    public ColdConfigurable() {
        settingsStorage = ServiceManager.getService(SettingsStorage.class);
    }


    @NotNull
    @Override
    public String getId() {
        return "plugins.tjxCold";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "TjxCold";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (configurationForm == null) {
            configurationForm = new ColdConfigurationForm(settingsStorage);
        }
        return configurationForm.getMainPane();
    }

    @Override
    public boolean isModified() {
        InfoConfig infoConfigInputs = configurationForm.getInfoConfigInputs();
        Map<String, TemplateConfig> configList = configurationForm.getTemplateConfigList();
        //校验
        return (infoConfigInputs == null || configList == null) ? false : true;
    }

    @Override
    public void apply() throws ConfigurationException {
        System.out.println("apply......");
        //保存 基本配置信息
        InfoConfig infoConfigInputs = configurationForm.getInfoConfigInputs();
        settingsStorage.setInfoConfig(infoConfigInputs);
        //保存模板配置信息
        Map<String, TemplateConfig> configList = configurationForm.getTemplateConfigList();
        settingsStorage.setTemplateConfigList(configList);
    }
}
