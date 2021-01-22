package com.healingtjx.cold.action;

import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.ui.NewModuleDialog;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.components.ServiceManager;

import java.awt.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:59 2021-01-11
 **/
public class NewModuleAction extends AnAction {

    private GenerateService generateService;


    private SettingsStorage settingsStorage;

    public NewModuleAction() {
        if (generateService == null) {
            generateService = ServiceManager.getService(GenerateService.class);
        }

        if (settingsStorage == null) {
            settingsStorage = ServiceManager.getService(SettingsStorage.class);

        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取当前目录
        IdeView ideView = e.getRequiredData(LangDataKeys.IDE_VIEW);
        String currentDirectory = ideView.getOrChooseDirectory().getVirtualFile().getPath();
        //显示界面
        showHintDialog(currentDirectory);
    }


    /**
     * 显示提示对话框
     */
    private void showHintDialog(String currentDirectory) {
        NewModuleDialog dialog = new NewModuleDialog(currentDirectory,generateService);
        dialog.setTitle("New Module");
        dialog.setPreferredSize(new Dimension(300, 180));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }


}
