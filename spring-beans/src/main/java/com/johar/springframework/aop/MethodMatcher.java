package com.johar.springframework.aop;

import java.lang.reflect.Method;

/**
 * @ClassName: MethodMatcher
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 10:55
 * @Since: 1.0.0
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
