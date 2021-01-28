package com.healingtjx.cold.ui;

import com.healingtjx.cold.entity.ModelEnum;
import com.healingtjx.cold.entity.PatternEnum;
import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.utils.StringUtil;
import com.intellij.openapi.ui.Messages;
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

    /**
     * 当前配置
     */
    SettingsStorage settingsStorage;

    /**
     * 当前策略
     */
    int generationStrategy;

    private GenerateService generateService;

    public NewModuleDialog(VirtualFile virtualFile, GenerateService generateService, SettingsStorage settingsStorage, int generationStrategy) {
        this.generationStrategy = generationStrategy;
        this.virtualFile = virtualFile;
        this.settingsStorage = settingsStorage;
        this.generateService = generateService;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSuccess);
        //判断当前模式
        int pattern = settingsStorage.getInfoConfig().getPattern();
        //只有 默认模式才需要配置 包名
        if (pattern == 0) {
            panel2.setVisible(false);
        }
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
        String packageName = packageTextField.getText();
        int pattern = settingsStorage.getInfoConfig().getPattern();
        if (pattern != ModelEnum.DEFAULT.getModel()) {
            //除开默认模式其他的都需要验证包名
            if (StringUtil.isNull(packageName)) {
                Messages.showMessageDialog("包名不能为空！", "提示", null);
                return;
            }
        }

        boolean selected = radioButton1.isSelected();
        String patternKey = selected ? PatternEnum.SIMPLE.getKey() : PatternEnum.INTRICACY.getKey();
        generateService.createTemplateCode(virtualFile.getPath(), name, packageName, patternKey, pattern, generationStrategy, settingsStorage);
        //刷新
        virtualFile.refresh(true, true);
        dispose();
    }

    /**
     * 取消事件
     */
    private void cancelListener() {
        dispose();
    }


}
