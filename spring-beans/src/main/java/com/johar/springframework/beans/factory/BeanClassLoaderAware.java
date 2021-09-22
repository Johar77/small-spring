package com.johar.springframework.beans.factory;

/**
 * @ClassName: BeanClassLoaderAware
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/22 08:45
 * @Since: 1.0.0
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
