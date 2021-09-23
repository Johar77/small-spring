package com.johar.springframework.beans.factory;

/**
 * @ClassName: FactoryBean
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 12:23
 * @Since: 1.0.0
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 判断是否是单例对象
     * @return
     */
    boolean isSingleton();
}
