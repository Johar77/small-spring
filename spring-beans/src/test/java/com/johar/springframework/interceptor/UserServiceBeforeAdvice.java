package com.johar.springframework.interceptor;

import com.johar.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName: UserServiceBeforeAdvice
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 15:18
 * @Since: 1.0.0
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}