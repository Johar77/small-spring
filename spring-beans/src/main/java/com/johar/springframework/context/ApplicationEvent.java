package com.johar.springframework.context;

import java.util.EventObject;

/**
 * @ClassName: ApplicationEvent
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:36
 * @Since: 1.0.0
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}