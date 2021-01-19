package com.healingtjx.cold.storage;

import com.healingtjx.cold.entity.InfoConfig;
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
     * 基础信息对应的 key
     */
    public static final String INFO_KEY = "INFO_CONFIG";


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
        InfoConfig infoConfig = new InfoConfig("controller","service","service.impl",0);
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
