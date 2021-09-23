package com.johar.springframework.beans.factory.config;

/**
 * @ClassName: SingletonBeanRegistry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:16
 * @Since: 1.0.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object bean);
}
