package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @ClassName: CglibSubclassingInstantiationStrategy
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:51
 * @Since: 1.0.0
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (constructor == null){
            return enhancer.create();
        }

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}