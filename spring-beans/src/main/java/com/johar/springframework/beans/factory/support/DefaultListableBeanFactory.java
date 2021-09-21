package com.johar.springframework.beans.factory.support;

import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.johar.springframework.beans.factory.DisposableBean;
import com.johar.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultListableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:28
 * @Since: 1.0.0
 */
public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null){
            throw new BeansException("No bean named " + name + " is defined");
        }

        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, bean) -> {
            Class beanClass = bean.getBeanClass();
            if (type.isAssignableFrom(beanClass)){
                result.put(beanName, (T)getBean(beanName));
            }
        });

        return result;
    }

    @Override
    public void preInstantiateSingleton() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}