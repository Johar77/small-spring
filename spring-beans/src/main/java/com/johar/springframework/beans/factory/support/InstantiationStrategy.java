package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @ClassName: InstantiationStrategy
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:45
 * @Since: 1.0.0
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition,
                       String beanName,
                       Constructor constructor,
                       Object[] args);
}
