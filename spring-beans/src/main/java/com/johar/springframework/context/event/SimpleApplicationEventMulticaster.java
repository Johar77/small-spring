package com.johar.springframework.context.event;

import com.johar.springframework.beans.factory.BeanFactory;
import com.johar.springframework.context.ApplicationEvent;
import com.johar.springframework.context.ApplicationListener;

/**
 * @ClassName: SimpleApplicationEventMulticaster
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 16:36
 * @Since: 1.0.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}