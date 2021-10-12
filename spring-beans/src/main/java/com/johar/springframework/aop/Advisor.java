package com.johar.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @ClassName: Advisor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:36
 * @Since: 1.0.0
 */
public interface Advisor {
    Advice getAdvice();
}
