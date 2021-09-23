package com.johar.springframework.context.event;

import com.johar.springframework.context.ApplicationContext;
import com.johar.springframework.context.ApplicationEvent;

/**
 * @ClassName: ApplicationContextEvent
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:38
 * @Since: 1.0.0
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}