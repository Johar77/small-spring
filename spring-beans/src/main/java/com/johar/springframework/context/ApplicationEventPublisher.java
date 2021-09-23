package com.johar.springframework.context;

/**
 * @ClassName: ApplicationEventPublisher
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:42
 * @Since: 1.0.0
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
