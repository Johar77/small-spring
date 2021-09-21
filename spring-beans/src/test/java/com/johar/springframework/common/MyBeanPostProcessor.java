package com.johar.springframework.common;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.UserService;
import com.johar.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName: MyBeanPostProcessor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 23:01
 * @Since: 1.0.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setWho("BeanPostProcessor");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}