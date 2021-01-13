package com.healingtjx.cold.ui;

import javax.swing.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于11:28 2021-01-13
 **/
public class TemplateEditPane {
    private JPanel     templateEdit;
    private JTextField templateNameText;
    private JSpinner   classNumberSpinner;
    private JTextField classNameText;
    private JTextField fileEncodingText;
    private JComboBox  templateLanguage;
    private JButton    testButton;
    private JComboBox  testInputs;
    private JSplitPane editorSplitPane;
    private JTextArea textArea1;
    private JButton showTestInput;
    private JComboBox targetLanguage;


    public JPanel getTemplateEdit() {
        return templateEdit;
    }
}
