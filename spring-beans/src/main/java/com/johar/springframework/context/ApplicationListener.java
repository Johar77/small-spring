package com.johar.springframework.context;

/**
 * @ClassName: ApplicationListener
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:43
 * @Since: 1.0.0
 */
public interface ApplicationListener<E extends ApplicationEvent> {

    void onApplicationEvent(E event);
}
