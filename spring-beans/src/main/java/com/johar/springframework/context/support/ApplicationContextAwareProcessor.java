package com.johar.springframework.context.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.config.BeanPostProcessor;
import com.johar.springframework.context.ApplicationContext;
import com.johar.springframework.context.ApplicationContextAware;

/**
 * @ClassName: ApplicationContextAwareProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/22 23:13
 * @Since: 1.0.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicationContextAware(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}