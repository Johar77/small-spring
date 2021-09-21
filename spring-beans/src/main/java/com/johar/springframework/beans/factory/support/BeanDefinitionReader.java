package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.core.io.Resource;
import com.johar.springframework.core.io.ResourceLoader;

/**
 * @ClassName: BeanDefinitionReader
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:32
 * @Since: 1.0.0
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
