package com.healingtjx.cold.action;

import com.healingtjx.cold.service.TestService;
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

    public TestAction(){
        if(testService == null){
            testService = ServiceManager.getService(TestService.class);
        }

        if(testStorage == null){
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
        testService.test();
        TestStorage.MyState state = testStorage.getState();
        System.out.println(state.getValue()+"test");


        showHintDialog();


    }


    /**
     * 显示提示对话框
     *
     */
    private void showHintDialog() {
        NewModuleDialog dialog = new NewModuleDialog();
        dialog.setTitle("New Module");
        dialog.setPreferredSize(new Dimension(300,180));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.requestFocus();
    }


}
