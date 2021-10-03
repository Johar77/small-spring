package com.johar.springframework.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName: UserServiceInterceptor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 22:31
 * @Since: 1.0.0
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称： " + methodInvocation.getMethod().getName());
            System.out.println("方法耗时:  " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End\r\n");
        }

    }
}