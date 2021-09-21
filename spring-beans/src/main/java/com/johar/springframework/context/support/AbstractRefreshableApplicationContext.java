package com.johar.springframework.context.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.johar.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @ClassName: AbstractRefreshableApplicationContext
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 17:11
 * @Since: 1.0.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}