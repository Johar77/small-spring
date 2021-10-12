package com.johar.springframework.aop.framework;

import com.johar.springframework.aop.AdvisedSupport;

/**
 * @ClassName: ProxyFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 14:50
 * @Since: 1.0.0
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }
}