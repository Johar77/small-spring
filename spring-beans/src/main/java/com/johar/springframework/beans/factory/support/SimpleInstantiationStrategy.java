package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName: SimpleInstantiationStrategy
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:44
 * @Since: 1.0.0
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try{
            if (constructor != null){
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new BeansException("failed to instantiate [" + beanName + "]", e);
        }
    }
}