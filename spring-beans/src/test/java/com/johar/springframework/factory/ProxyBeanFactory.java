package com.johar.springframework.factory;

import com.johar.springframework.beans.factory.FactoryBean;
import com.johar.springframework.dao.Company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ProxyBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 12:55
 * @Since: 1.0.0
 */
public class ProxyBeanFactory implements FactoryBean<Company> {
    @Override
    public Company getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new ConcurrentHashMap<>();
            hashMap.put("1", "一区D3E11");

            return "你被代理了" + method.getName() + ": " + hashMap.get(args[0].toString());
        };
        return (Company) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Company.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}