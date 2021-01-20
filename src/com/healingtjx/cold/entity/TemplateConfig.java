package com.healingtjx.cold.entity;

import java.io.Serializable;


/**
 * @Author: tjx
 * @Description: 模板配置
 * @Date: 创建于11:30 2021-01-19
 **/
public class TemplateConfig implements Serializable {


    public TemplateConfig(String controllerTemplate, String serviceTemplate, String implTemplate) {
        this.controllerTemplate = controllerTemplate;
        this.serviceTemplate = serviceTemplate;
        this.implTemplate = implTemplate;
    }

    /**
     * 控制层包模板
     */
    private String controllerTemplate;

    /**
     * 业务层包模板
     */
    private String serviceTemplate;

    /**
     * 业务层实现类包模板
     */
    private String implTemplate;

    public String getControllerTemplate() {
        return controllerTemplate;
    }

    public void setControllerTemplate(String controllerTemplate) {
        this.controllerTemplate = controllerTemplate;
    }

    public String getServiceTemplate() {
        return serviceTemplate;
    }

    public void setServiceTemplate(String serviceTemplate) {
        this.serviceTemplate = serviceTemplate;
    }

    public String getImplTemplate() {
        return implTemplate;
    }

    public void setImplTemplate(String implTemplate) {
        this.implTemplate = implTemplate;
    }


    /**
     * 根据 key 获取
     *
     * @param key
     * @return
     */
    public String getByKey(String key) {
        String value = null;
        if (TemplateItemEnum.CONTROLLER.getName().equals(key)) {
            value = this.controllerTemplate;
        }
        if (TemplateItemEnum.SERVICE.getName().equals(key)) {
            value = this.serviceTemplate;
        }
        if (TemplateItemEnum.IMPL.getName().equals(key)) {
            value = this.implTemplate;
        }
        return value;

    }

    /**
     * 根据 key 设置
     *
     * @param key
     */
    public void setByKey(String key, String value) {
        if (TemplateItemEnum.CONTROLLER.getName().equals(key)) {
            this.controllerTemplate = value;
        }
        if (TemplateItemEnum.SERVICE.getName().equals(key)) {
            this.serviceTemplate = value;
        }
        if (TemplateItemEnum.IMPL.getName().equals(key)) {
            this.implTemplate = value;
        }
    }
}
