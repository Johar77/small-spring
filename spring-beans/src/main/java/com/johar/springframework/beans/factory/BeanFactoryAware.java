package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: BeanFactoryAware
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/22 08:35
 * @Since: 1.0.0
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
