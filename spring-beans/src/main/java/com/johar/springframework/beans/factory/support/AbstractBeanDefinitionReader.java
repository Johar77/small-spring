package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.core.io.DefaultResourceLoader;
import com.johar.springframework.core.io.Resource;
import com.johar.springframework.core.io.ResourceLoader;

/**
 * @ClassName: AbstractBeanDefinitionReader
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:41
 * @Since: 1.0.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;

    private final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}