package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.DisposableBean;
import com.johar.springframework.beans.factory.ObjectFactory;
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
    /**
     * 一级缓存，普通的对象
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 二级缓存，提前暴露对象，没有完全实例化的对象
     */
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    /**
     * 三级缓存，存放代理对象
     */
    private Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();

    private Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        // 判断一级缓存
        if (singletonObject == null){
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存
            if (singletonObject == null){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null){
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存的代理对象中真实的对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }

        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object bean){
        singletonObjects.put(beanName, bean);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)){
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
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