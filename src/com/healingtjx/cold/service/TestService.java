package com.healingtjx.cold.service;

import com.intellij.openapi.components.ServiceManager;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于16:46 2021-01-15
 **/
public interface TestService {
    static TestService getInstance() {
        return ServiceManager.getService(TestService.class);
    }


    void test();
}
