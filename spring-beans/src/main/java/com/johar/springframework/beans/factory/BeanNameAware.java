package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: BeanNameAware
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/22 08:46
 * @Since: 1.0.0
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
