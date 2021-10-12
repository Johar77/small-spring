package com.johar.springframework.beans.factory.config;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: InstantiationAwareBeanPostProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:55
 * @Since: 1.0.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
