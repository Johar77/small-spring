package com.johar.springframework.beans.factory.config;

/**
 * @ClassName: BeanReference
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 21:20
 * @Since: 1.0.0
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}