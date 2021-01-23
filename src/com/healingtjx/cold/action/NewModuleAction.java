package com.healingtjx.cold.action;

import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.ui.NewModuleDialog;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.vfs.VirtualFile;

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
        VirtualFile virtualFile = ideView.getOrChooseDirectory().getVirtualFile();
        //显示界面
        showHintDialog(virtualFile);

    }


    /**
     * 显示提示对话框
     */
    private void showHintDialog(VirtualFile virtualFile) {
        NewModuleDialog dialog = new NewModuleDialog(virtualFile, generateService);
        dialog.setTitle("New Module");
        dialog.setPreferredSize(new Dimension(300, 180));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }


}