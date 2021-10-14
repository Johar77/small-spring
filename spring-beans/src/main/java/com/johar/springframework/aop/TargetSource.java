package com.johar.springframework.aop;

import com.johar.springframework.utils.ClassUtils;

/**
 * @ClassName: TargetSource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 18:28
 * @Since: 1.0.0
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget(){
        return this.target;
    }
}