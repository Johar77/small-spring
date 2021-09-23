package com.johar.springframework.context.event;

import com.johar.springframework.context.ApplicationEvent;
import com.johar.springframework.context.ApplicationListener;

/**
 * @ClassName: ApplicationEventMulticaster
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:40
 * @Since: 1.0.0
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
