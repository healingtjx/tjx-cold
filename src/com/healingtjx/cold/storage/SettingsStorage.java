package com.healingtjx.cold.storage;

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
    @Nullable
    @Override
    public SettingsStorage getState() {

        if (codeTemplates == null) {
            loadDefaultSettings();
        }
        return this;
    }

    private Map<String, String> codeTemplates;


    private void loadDefaultSettings() {
        codeTemplates = new HashMap<>();
        codeTemplates.put("test", "tjx");
    }


    @Override
    public void loadState(@NotNull SettingsStorage settingsStorage) {
        XmlSerializerUtil.copyBean(settingsStorage, this);
    }


    public void setCodeTemplates(Map<String, String> codeTemplates) {
        this.codeTemplates = codeTemplates;
    }

    public Map<String, String> getCodeTemplates() {
        return codeTemplates;
    }
}
