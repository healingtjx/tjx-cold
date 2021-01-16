package com.healingtjx.cold.service.impl;

import com.healingtjx.cold.service.TestService;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于16:46 2021-01-15
 **/
public class TestServiceImpl implements TestService {

    @Override
    public void test() {
        System.out.println("调用了");
    }
}
