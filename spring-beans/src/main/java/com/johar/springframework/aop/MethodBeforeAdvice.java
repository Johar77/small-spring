package com.johar.springframework.aop;

import java.lang.reflect.Method;

/**
 * @ClassName: MethodBeforeAdvice
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:34
 * @Since: 1.0.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
