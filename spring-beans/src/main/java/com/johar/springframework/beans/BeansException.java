package com.johar.springframework.beans;

/**
 * @ClassName: BeansException
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 16:53
 * @Since: 1.0.0
 */
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}