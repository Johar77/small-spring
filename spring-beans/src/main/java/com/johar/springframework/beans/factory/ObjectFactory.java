package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: ObjectFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:00
 * @Since: 1.0.0
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
