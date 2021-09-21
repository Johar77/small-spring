package com.johar.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.DisposableBean;
import com.johar.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @ClassName: DisposableBeanAdapter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 22:17
 * @Since: 1.0.0
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1.实现接口 DisposableBean
        if (bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        // 2.注解配置destroy-method
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null){
                throw new BeansException("Could not find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}