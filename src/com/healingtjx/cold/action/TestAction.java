package com.healingtjx.cold.action;

import com.healingtjx.cold.service.TestService;
import com.healingtjx.cold.storage.SettingsStorage;
import com.healingtjx.cold.storage.TestStorage;
import com.healingtjx.cold.ui.NewModuleDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;

import java.awt.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:59 2021-01-11
 **/
public class TestAction extends AnAction {

    private TestService testService;

    private TestStorage testStorage;

    private SettingsStorage settingsStorage;

    public TestAction() {
        if (testService == null) {
            testService = ServiceManager.getService(TestService.class);
        }

        if (settingsStorage == null) {
            settingsStorage = ServiceManager.getService(SettingsStorage.class);
        }

        if (testStorage == null) {
            TestStorage.MyState state = new TestStorage.MyState();
            state.setValue("123123123");
            testStorage = new TestStorage();
            testStorage.loadState(state);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //模拟调用service
//        Map<String, Object> codeTemplates = settingsStorage.getCodeTemplates();

//        System.out.println("配置数据"+new Gson().toJson(codeTemplates));
        showHintDialog();


    }


    /**
     * 显示提示对话框
     */
    private void showHintDialog() {
        NewModuleDialog dialog = new NewModuleDialog();
        dialog.setTitle("New Module");
        dialog.setPreferredSize(new Dimension(300, 180));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }


}
