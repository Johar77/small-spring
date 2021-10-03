package com.johar.springframework.aop;

/**
 * @ClassName: ClassFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 10:55
 * @Since: 1.0.0
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
