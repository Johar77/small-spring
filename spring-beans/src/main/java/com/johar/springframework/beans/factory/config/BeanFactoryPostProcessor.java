package com.johar.springframework.beans.factory.config;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @ClassName: BeanFactoryPostProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 11:19
 * @Since: 1.0.0
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition 加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
