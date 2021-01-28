package com.healingtjx.cold.ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healingtjx.cold.entity.PatternEnum;
import com.healingtjx.cold.entity.SelectItem;
import com.healingtjx.cold.entity.TemplateConfig;
import com.healingtjx.cold.entity.TemplateItemEnum;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

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
    private JButton importButton;
    private JComboBox<SelectItem> patternComboBox;
    private JComboBox templateComboBox;
    private JButton exportButton;
    private JButton showTestInput;
    private JComboBox targetLanguage;


    private Map<String, TemplateConfig> templateConfigList;
    private TemplateConfig currentTemplate;

    public TemplateEditPane(Map<String, TemplateConfig> templateConfigList) {

        if (templateConfigList == null) {
            return;
        }
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
        //导入导出
        importButton.addActionListener(e -> importListener(e));
        exportButton.addActionListener(e -> exportListener(e));


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
        //根据位置获取内容
        String templateItemKey = (String) templateComboBox.getSelectedItem();
        String value = templateConfig.getByKey(templateItemKey);
        textArea.setText(value);
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

        if (e.getStateChange() == ItemEvent.DESELECTED) {
            SelectItem item = (SelectItem) patternComboBox.getSelectedItem();
            String templateItemKey = (String) e.getItem();
            isModify(item.getKey(), templateItemKey);
        }

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
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            SelectItem item = (SelectItem) e.getItem();
            String templateItemKey = (String) templateComboBox.getSelectedItem();
            isModify(item.getKey(), templateItemKey);

        }

        if (e.getStateChange() == ItemEvent.SELECTED) {
            SelectItem item = (SelectItem) e.getItem();
            loadByTemplateConfigByKey(item.getKey());
        }
    }


    /**
     * 导入配置
     *
     * @param event
     */
    public void importListener(ActionEvent event) {
        //实例化json相关
        JsonParser parse = new JsonParser();
        Gson gson = new Gson();
        String json = Messages.showInputDialog("导入", "导入", null);
        if (json == null || json.trim().length() == 0) {
            return;
        }
        try {
            //解析json 封装新的配置
            Map<String, TemplateConfig> newTemplateConfigList = new HashMap<>(8);
            JsonObject jsonObject = (JsonObject) parse.parse(json);
            JsonElement simpleJson = jsonObject.get(PatternEnum.SIMPLE.getKey());
            newTemplateConfigList.put(PatternEnum.SIMPLE.getKey(), gson.fromJson(simpleJson, TemplateConfig.class));
            JsonElement complexJson = jsonObject.get(PatternEnum.INTRICACY.getKey());
            newTemplateConfigList.put(PatternEnum.INTRICACY.getKey(), gson.fromJson(complexJson, TemplateConfig.class));
            //刷新到当前配置
            this.templateConfigList = newTemplateConfigList;

            //复原下拉框
            patternComboBox.setSelectedIndex(0);
            templateComboBox.setSelectedIndex(0);

            //刷新当前配置
            loadByTemplateConfigByKey(PatternEnum.SIMPLE.getKey());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.showMessageDialog("配置格式错误", "错误提示", null);
        }
    }

    /**
     * 导出配置
     *
     * @param e
     */
    public void exportListener(ActionEvent e) {
        //获取当前配置
        String json = new Gson().toJson(templateConfigList);
        Messages.showInputDialog("导出", "导出", null, json, null);
    }


    /**
     * 保存事件
     *
     * @param e
     */
    public void saveListener(ActionEvent e) {
        String text = textArea.getText();
        SelectItem patternItem = (SelectItem) patternComboBox.getSelectedItem();
        String templateItemKey = (String) templateComboBox.getSelectedItem();
        setTemplateBySelectIndex(patternItem.getKey(), templateItemKey, text);
    }


    /**
     * 根据selectIndex 保存数据
     *
     * @param patternKey
     * @param templateKey
     * @param text
     */
    public void setTemplateBySelectIndex(String patternKey, String templateKey, String text) {
        TemplateConfig templateConfig = templateConfigList.get(patternKey);
        templateConfig.setByKey(templateKey, text);
    }


    /**
     * 是否修改
     *
     * @param patternKey
     * @param templateItemKey
     */
    public void isModify(String patternKey, String templateItemKey) {
        TemplateConfig templateConfig = templateConfigList.get(patternKey);
        String value = templateConfig.getByKey(templateItemKey);
        String text = textArea.getText();
        if (!value.equals(text)) {
            int i = Messages.showOkCancelDialog("是否需要保存?", "有修改的内容", null);
            if (i == 0) {
                //同意保存
                templateConfig.setByKey(templateItemKey, text);
            }
        }


    }

    /**
     * 获取修改后的数据
     *
     * @return
     */
    public Map<String, TemplateConfig> getTemplateConfigList() {
        return templateConfigList;
    }

}
