package com.johar.springframework.test.reference;

import com.johar.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName: SpouseAdvice
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:49
 * @Since: 1.0.0
 */
public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口子（切面）：" + method);
    }
}