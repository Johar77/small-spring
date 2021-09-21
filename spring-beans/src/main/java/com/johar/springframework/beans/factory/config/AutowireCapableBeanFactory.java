package com.johar.springframework.beans.factory.config;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.BeanFactory;

/**
 * @ClassName: AutowireCapableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 13:40
 * @Since: 1.0.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
