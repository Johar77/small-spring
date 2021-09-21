package com.johar.springframework.beans.factory.config;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: BeanPostProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 11:20
 * @Since: 1.0.0
 */
public interface BeanPostProcessor {

    /**
     * 在Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
