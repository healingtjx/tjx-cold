package com.healingtjx.cold.action;

import com.healingtjx.cold.entity.GenerationStrategyEnum;
import com.healingtjx.cold.service.GenerateService;
import com.healingtjx.cold.utils.HintDialogUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于15:28 2021-01-26
 **/
public class NewControllerAction extends AnAction {


    private GenerateService generateService;


    public NewControllerAction() {
        if (generateService == null) {
            generateService = ServiceManager.getService(GenerateService.class);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        //显示界面
        HintDialogUtil.showHintDialog(e, generateService, GenerationStrategyEnum.ONLY_CONTROLLER.getStrategy());
    }
}
