package com.johar.springframework.context.event;

/**
 * @ClassName: ContextClosedEvent
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:39
 * @Since: 1.0.0
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}