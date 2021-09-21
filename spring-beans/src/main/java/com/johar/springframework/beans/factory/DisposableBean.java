package com.johar.springframework.beans.factory;

import com.johar.springframework.beans.BeansException;

/**
 * @ClassName: DisposableBean
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 16:18
 * @Since: 1.0.0
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
