package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultSingletonBeanRegistry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:18
 * @Since: 1.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }


    protected void addSingleton(String beanName, Object bean){
        singletonObjects.put(beanName, bean);
    }
}