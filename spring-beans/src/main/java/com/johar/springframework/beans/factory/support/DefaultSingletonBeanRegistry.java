package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.DisposableBean;
import com.johar.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultSingletonBeanRegistry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:18
 * @Since: 1.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }


    protected void addSingleton(String beanName, Object bean){
        singletonObjects.put(beanName, bean);
    }

    public void registerDisposableBean(String beanName, DisposableBean disposableBean){
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeanMap.keySet();
        String[] disposableBeanNames = keySet.toArray(new String[0]);
        for (int i = disposableBeanNames.length - 1; i >= 0; i--){
            String beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try{
                disposableBean.destroy();
            } catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName
                + "' failed", e);
            }
        }
    }
}