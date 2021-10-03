package com.johar.springframework.aop;

/**
 * @ClassName: Pointcut
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 10:54
 * @Since: 1.0.0
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
