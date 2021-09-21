package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.BeanFactory;
import com.johar.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName: AbstractBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:21
 * @Since: 1.0.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(String name, Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T)bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name);

    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);
}