package com.johar.springframework.beans.factory;

/**
 * @ClassName: InitializingBean
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 22:03
 * @Since: 1.0.0
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
