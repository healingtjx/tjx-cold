package com.healingtjx.cold.ui;

import javax.swing.*;

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
    private JTextArea textArea1;
    private JButton submitButton;
    private JButton resetButton;
    private JComboBox patternComboBox;
    private JButton showTestInput;
    private JComboBox targetLanguage;


    public TemplateEditPane() {
        patternComboBox.addItem(new String("简单"));
        patternComboBox.addItem(new String("复杂"));
    }


    public JPanel getTemplateEdit() {
        return templateEdit;
    }


}
