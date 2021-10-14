package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: BeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:15
 * @Since: 1.0.0
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);
}
