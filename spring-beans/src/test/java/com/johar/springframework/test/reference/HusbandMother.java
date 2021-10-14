package com.johar.springframework.test.reference;

import com.johar.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @ClassName: HusbandMother
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:49
 * @Since: 1.0.0
 */
public class HusbandMother implements FactoryBean<IMother> {
    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{ IMother.class},
                ((proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了" + method.getName()));
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}