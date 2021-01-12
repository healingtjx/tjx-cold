package com.healingtjx.cold.action;

import com.healingtjx.cold.ui.NewModuleDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:59 2021-01-11
 **/
public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        showHintDialog();
    }


    /**
     * 显示提示对话框
     *
     */
    private void showHintDialog() {
        NewModuleDialog dialog = new NewModuleDialog();
        dialog.setTitle("New Module");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }

}
