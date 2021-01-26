package com.healingtjx.cold.entity;

/**
 * @Author: tjx
 * @Description: 生成策略
 * @Date: 创建于15:37 2021-01-26
 **/
public enum GenerationStrategyEnum {
    /**
     * 全部生成
     */
    ALL(0),

    /**
     * 只生成 controller
     */
    ONLY_CONTROLLER(1),
    /**
     * 只生成 service,impl
     */
    ONLY_SERVICE(2);


    private int strategy;

    GenerationStrategyEnum(int strategy) {
        this.strategy = strategy;
    }

    public int getStrategy() {
        return strategy;
    }
}
