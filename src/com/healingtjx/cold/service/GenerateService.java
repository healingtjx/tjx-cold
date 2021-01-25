package com.healingtjx.cold.service;

/**
 * @Author: tjx
 * @Description: 生成代码service
 * @Date: 创建于16:46 2021-01-15
 **/
public interface GenerateService {

    /**
     * 生成模块代码
     * @param filePath 生成文件路径
     * @param name     模块名称
     */
    void createTemplateCode(String filePath,String name,String patternKey);
}
