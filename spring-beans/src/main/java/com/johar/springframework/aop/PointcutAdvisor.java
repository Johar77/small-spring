package com.johar.springframework.aop;


/**
 * @ClassName: PointcutAdvisor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:37
 * @Since: 1.0.0
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
