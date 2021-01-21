package com.healingtjx.cold.ui;

import javax.swing.*;
import java.awt.event.*;

/**
 * @Author: tjx
 * @Description: new module 界面
 * @Date: 创建于10:59 2021-01-12
 **/
public class NewModuleDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSuccess;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private ButtonGroup buttonGroup;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public NewModuleDialog() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSuccess);

        buttonSuccess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSuccess();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSuccess() {

        System.out.println("cheng l ");
        dispose();
    }

    private void onCancel() {
        dispose();
    }


}
