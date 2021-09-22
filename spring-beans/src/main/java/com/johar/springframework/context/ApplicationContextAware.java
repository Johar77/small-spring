package com.johar.springframework.context;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.Aware;

/**
 * @ClassName: ApplicationContextAware
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/22 08:48
 * @Since: 1.0.0
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContextAware(ApplicationContext applicationContext) throws BeansException;
}
