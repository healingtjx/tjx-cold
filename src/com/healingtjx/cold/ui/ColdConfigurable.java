package com.healingtjx.cold.ui;

import com.healingtjx.cold.storage.SettingsStorage;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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
        System.out.println("isModified . . .. . .");
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        System.out.println("apply . . .. . .");
    }
}
