package com.johar.springframework.common;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.PropertyValue;
import com.johar.springframework.beans.PropertyValues;
import com.johar.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @ClassName: MyBeanFactoryProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 22:59
 * @Since: 1.0.0
 */
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("age", 31));
    }
}