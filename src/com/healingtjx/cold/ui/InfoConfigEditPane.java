package com.healingtjx.cold.ui;

import com.healingtjx.cold.entity.InfoConfig;

import javax.swing.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:38 2021-01-13
 **/
public class InfoConfigEditPane {
    private JPanel panel;
    private JTextField controllerText;
    private JTextField serviceText;
    private JTextField implText;
    private JRadioButton defaultPattern;
    private JRadioButton onePattern;
    private JRadioButton twoPattern;
    private ButtonGroup group;


    public InfoConfigEditPane(InfoConfig config) {
        loadByConfig(config);
    }

    /**
     * 加载配置信息
     */
    public void loadByConfig(InfoConfig config) {
        controllerText.setText(config.getControllerPackage());
        serviceText.setText(config.getServicePackage());
        implText.setText(config.getImplPackage());
        //选择模式
        group = new ButtonGroup();
        group.add(defaultPattern);
        group.add(onePattern);
        group.add(twoPattern);
        int pattern = config.getPattern();
        switch (pattern) {
            case 1: {
                onePattern.setSelected(true);
                break;
            }
            case 2: {
                twoPattern.setSelected(true);
                break;
            }
            default: {
                defaultPattern.setSelected(true);
                break;
            }
        }
    }

    /**
     * 获取 当前页面input 内容
     *
     * @return
     */
    public InfoConfig getInputs() {
        boolean defaultSelected = defaultPattern.isSelected();
        boolean oneSelected = onePattern.isSelected();
        return new InfoConfig(controllerText.getText(), serviceText.getText(), implText.getText()
                , defaultSelected ? 0 : (oneSelected ? 1 : 2));
    }


    public JPanel getPanel() {
        return panel;
    }

}
