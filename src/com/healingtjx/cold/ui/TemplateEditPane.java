package com.healingtjx.cold.ui;

import com.google.gson.Gson;
import com.healingtjx.cold.entity.PatternEnum;
import com.healingtjx.cold.entity.SelectItem;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.entity.TemplateItemEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于11:28 2021-01-13
 **/
public class TemplateEditPane {
    private JPanel templateEdit;
    private JTextField templateNameText;
    private JSpinner classNumberSpinner;
    private JTextField classNameText;
    private JTextField fileEncodingText;
    private JComboBox templateLanguage;
    private JButton testButton;
    private JComboBox testInputs;
    private JSplitPane editorSplitPane;
    private JPanel panel1;
    private JTextArea textArea;
    private JButton submitButton;
    private JButton resetButton;
    private JComboBox<SelectItem> patternComboBox;
    private JComboBox templateComboBox;
    private JButton showTestInput;
    private JComboBox targetLanguage;


    private Map<String, TemplateConfig> templateConfigList;
    private TemplateConfig currentTemplate;

    public TemplateEditPane(Map<String, TemplateConfig> templateConfigList) {
        //加载数据
        this.templateConfigList = templateConfigList;

        //实例化下拉列表
        patternComboBox.addItem(new SelectItem(PatternEnum.SIMPLE.getName(), PatternEnum.SIMPLE.getKey()));
        patternComboBox.addItem(new SelectItem(PatternEnum.INTRICACY.getName(), PatternEnum.INTRICACY.getKey()));
        templateComboBox.addItem(new String(TemplateItemEnum.CONTROLLER.getName()));
        templateComboBox.addItem(new String(TemplateItemEnum.SERVICE.getName()));
        templateComboBox.addItem(new String(TemplateItemEnum.IMPL.getName()));

        //模式选择切换事件
        patternComboBox.addItemListener(e -> patternComboBoxChangeListener(e));
        //保存事件
        submitButton.addActionListener(e -> saveListener(e));

        //加载简单模式数据
        loadByTemplateConfigByKey(PatternEnum.SIMPLE.getKey());
    }


    public JPanel getTemplateEdit() {
        return templateEdit;
    }


    /**
     * 根据 模板配置加载数据
     */
    private void loadByTemplateConfigByKey(String key) {
        TemplateConfig templateConfig = templateConfigList.get(key);
        String controllerTemplate = templateConfig.getControllerTemplate();
        textArea.setText(controllerTemplate);
        this.currentTemplate = templateConfig;
        //加入监听 代码块切换
        templateComboBox.addItemListener(e -> templateComboBoxChangeListener(e));

    }


    /**
     * 代码块选择切换事件
     *
     * @param e
     */
    public void templateComboBoxChangeListener(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String key = (String) e.getItem();
            String value = currentTemplate.getByKey(key);
            textArea.setText(value);
        }
    }

    /**
     * 模式选择切换事件
     *
     * @param e
     */
    public void patternComboBoxChangeListener(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            SelectItem item = (SelectItem) e.getItem();
            loadByTemplateConfigByKey(item.getKey());
        }
    }


    /**
     * 保存事件
     *
     * @param e
     */
    public void saveListener(ActionEvent e) {
        System.out.println(new Gson().toJson(e));
        String text = textArea.getText();
        System.out.println("text:" + text);

        int patternSelectedIndex = patternComboBox.getSelectedIndex();
        int templateSelectedIndex = templateComboBox.getSelectedIndex();

        System.out.println(patternSelectedIndex + "\t" + templateSelectedIndex);
    }


}
