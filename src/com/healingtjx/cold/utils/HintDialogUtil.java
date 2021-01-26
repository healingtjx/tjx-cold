package com.healingtjx.cold.utils;

import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.ui.NewModuleDialog;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.awt.*;

/**
 * @Author: tjx
 * @Description: 封装展示 Dialog
 * @Date: 创建于15:46 2021-01-26
 **/
public class HintDialogUtil {


    /**
     * 显示提示对话框
     */
    public static void showHintDialog(AnActionEvent actionEvent, GenerateService generateService, int generationStrategy) {
        //获取当前目录
        IdeView ideView = actionEvent.getRequiredData(LangDataKeys.IDE_VIEW);
        VirtualFile virtualFile = ideView.getOrChooseDirectory().getVirtualFile();
        //获取当前项目
        SettingsStorage currSettingsStorage = ManagerUtil.getCurrSettingsStorage();
        //实例化窗口
        NewModuleDialog dialog = new NewModuleDialog(virtualFile, generateService, currSettingsStorage, generationStrategy);
        dialog.setTitle("New Module");
        dialog.setPreferredSize(new Dimension(300, 180));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }
}
