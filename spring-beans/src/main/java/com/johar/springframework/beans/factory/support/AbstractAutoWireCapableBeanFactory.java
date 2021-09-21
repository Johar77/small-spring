package com.johar.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.PropertyValue;
import com.johar.springframework.beans.PropertyValues;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName: AbstractAutoWireCapableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:25
 * @Since: 1.0.0
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, name, args);
            applyPropertyValues(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(name, bean);
        return bean;
    }

    /**
     *  Bean属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    // TODO 考虑循环依赖
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e){
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    /**
     * 创建Bean实例，支持无参、有参构造函数
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructorToUse = null;
        if (args != null) {
            Class<?> beanClass = beanDefinition.getBeanClass();
            Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

            Optional<Constructor<?>> matchedConstructor = Arrays.stream(declaredConstructors).filter(c -> matchConstructor(c, args)).findFirst();
            if (matchedConstructor.isPresent()){
               constructorToUse = matchedConstructor.get();
            }
        }

        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 判断该构造函数是否和入参匹配
     * @param constructor
     * @param args
     * @return
     */
    private boolean matchConstructor(Constructor constructor, Object[] args){
        if (args == null || constructor.getParameterTypes().length != args.length){
            return false;
        }

        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < args.length; i++){
            if (!args[i].getClass().isAssignableFrom(parameterTypes[i])){
                return false;
            }
        }

        return true;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}