package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName: BeanDefinitionRegistry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:17
 * @Since: 1.0.0
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();
}
