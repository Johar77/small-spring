package com.johar.springframework.aop.framework;

import com.johar.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: JdkDynamicAopProxy
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/3 21:43
 * @Since: 1.0.0
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advisedSupport
                .getMethodMatcher()
                .matches(method, advisedSupport.getTargetSource().getTargetClass().getClass())) {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }

        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}