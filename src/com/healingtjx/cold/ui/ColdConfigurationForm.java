package com.healingtjx.cold.ui;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.storage.SettingsStorage;

import javax.swing.*;
import java.util.Map;

/**
 * @Author: tjx
 * @Description: 配置页面 tabbePane
 * @Date: 创建于10:30 2021-01-13
 **/
public class ColdConfigurationForm {
    private JTabbedPane tabbedPane;
    private JPanel mainPane;

    private InfoConfigEditPane infoConfigEditPane;
    private TemplateEditPane templateEditPane;

    public ColdConfigurationForm(SettingsStorage settingsStorage) {
        // 获取配置信息
        InfoConfig infoConfig = settingsStorage.getInfoConfig();
        Map<String, TemplateConfig> templateConfigList = settingsStorage.getTemplateConfigList();
        infoConfigEditPane = new InfoConfigEditPane(infoConfig);
        templateEditPane = new TemplateEditPane(templateConfigList);
        tabbedPane.insertTab("信息配置", null, infoConfigEditPane.getPanel(), null, 0);
        tabbedPane.insertTab("模板配置", null, templateEditPane.getTemplateEdit(), null, 1);
    }


    /**
     * 获取 主容器
     *
     * @return
     */
    public JPanel getMainPane() {
        return mainPane;
    }


    /**
     * 获取 info配置信息
     *
     * @return
     */
    public InfoConfig getInfoConfigInputs() {
        return infoConfigEditPane.getInputs();
    }


    /**
     * 获取 模板配置数据
     *
     * @return
     */
    public Map<String, TemplateConfig> getTemplateConfigList() {
        return templateEditPane.getTemplateConfigList();
    }
}
