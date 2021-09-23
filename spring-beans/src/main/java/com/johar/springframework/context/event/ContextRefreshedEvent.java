package com.johar.springframework.context.event;

/**
 * @ClassName: ContextRefreshedEvent
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 13:40
 * @Since: 1.0.0
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}