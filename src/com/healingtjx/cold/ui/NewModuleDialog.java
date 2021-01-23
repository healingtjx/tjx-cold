package com.healingtjx.cold.ui;

import com.healingtjx.cold.service.GenerateService;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: tjx
 * @Description: new module 界面
 * @Date: 创建于10:59 2021-01-12
 **/
public class NewModuleDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSuccess;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JTextField packageTextField;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private ButtonGroup buttonGroup;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;


    /**
     * 当前选择目录
     */
    VirtualFile virtualFile;
    private GenerateService generateService;

    public NewModuleDialog(VirtualFile virtualFile, GenerateService generateService) {
        this.virtualFile = virtualFile;
        this.generateService = generateService;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSuccess);
        //设置事件监听
        buttonSuccess.addActionListener(e -> successListener());
        buttonCancel.addActionListener(e -> cancelListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelListener();
            }
        });
        contentPane.registerKeyboardAction(e -> cancelListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * 确认事件
     */
    private void successListener() {
        String name = nameTextField.getText();
        generateService.createTemplateCode(virtualFile.getPath(), name);
        //刷新
        virtualFile.refresh(true,true);
        dispose();
    }

    /**
     * 取消事件
     */
    private void cancelListener() {
        dispose();
    }


}
