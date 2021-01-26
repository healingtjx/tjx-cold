package com.healingtjx.cold.utils;

import com.healingtjx.cold.storage.SettingsStorage;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.WindowManager;

import java.awt.*;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于12:11 2021-01-26
 **/
public class ManagerUtil {


    /**
     * 获取当前项目
     *
     * @return
     */
    public static Project getCurrProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project[] openProjects = projectManager.getOpenProjects();
        if (openProjects.length == 0) {
            //没有打开项目
            return projectManager.getDefaultProject();
        } else if (openProjects.length == 1) {
            // 只存在一个打开的项目则使用打开的项目
            return openProjects[0];
        }

        //如果有项目窗口处于激活状态
        try {
            WindowManager wm = WindowManager.getInstance();
            for (Project project : openProjects) {
                Window window = wm.suggestParentWindow(project);
                if (window != null && window.isActive()) {
                    return project;
                }
            }
        } catch (Exception ignored) {
        }
        //否则使用默认项目
        return projectManager.getDefaultProject();
    }

    /**
     * 获取当前存储配置
     *
     * @return
     */
    public static SettingsStorage getCurrSettingsStorage() {
        Project project = getCurrProject();
        SettingsStorage settingsStorage = ServiceManager.getService(project, SettingsStorage.class);
        settingsStorage.getState();
        return settingsStorage;
    }
}
