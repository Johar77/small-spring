package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.config.BeanPostProcessor;
import com.johar.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @ClassName: ConfigurableListableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 13:38
 * @Since: 1.0.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingleton() throws BeansException;
}
