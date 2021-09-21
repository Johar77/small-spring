package com.johar.springframework.context;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: ConfigurableApplicationContext
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 11:28
 * @Since: 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
