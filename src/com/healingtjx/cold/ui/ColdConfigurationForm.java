package com.healingtjx.cold.ui;

import com.healingtjx.cold.entity.InfoConfig;
import com.healingtjx.cold.storage.SettingsStorage;

import javax.swing.*;

/**
 * @Author: tjx
 * @Description: 配置页面 tabbePane
 * @Date: 创建于10:30 2021-01-13
 **/
public class ColdConfigurationForm {
    private JTabbedPane tabbedPane;
    private JPanel mainPane;

    public ColdConfigurationForm(SettingsStorage settingsStorage) {
        // 获取配置信息
        InfoConfig infoConfig = settingsStorage.getInfoConfig();
        tabbedPane.insertTab("信息配置",null,new InfoConfigEditPane(infoConfig).getPanel(),null,0);
        tabbedPane.insertTab("模板配置",null,new TemplateEditPane().getTemplateEdit(),null,1);
    }

    /**
     * 获取 主容器
     *
     * @return
     */
    public JPanel getMainPane() {
        return mainPane;
    }

}
