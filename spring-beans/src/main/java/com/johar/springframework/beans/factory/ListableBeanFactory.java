package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

import java.util.Map;

/**
 * @ClassName: ListableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 13:34
 * @Since: 1.0.0
 */
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
