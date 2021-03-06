package com.johar.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.PropertyValue;
import com.johar.springframework.beans.PropertyValues;
import com.johar.springframework.beans.factory.*;
import com.johar.springframework.beans.factory.config.*;
import com.johar.springframework.core.convert.support.ConversionService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName: AbstractAutoWireCapableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:25
 * @Since: 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();



    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);;
        if (bean != null){
            return bean;
        }

        return doCreateBean(beanName, beanDefinition, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args){
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);

            if (beanDefinition.isSingleton()){
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }

            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation){
                return bean;
            }

            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);

            applyPropertyValues(beanName, bean, beanDefinition);

            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        Object exposedObject = bean;
        if (beanDefinition.isSingleton()){
            exposedObject = getSingleton(beanName);
            registerSingleton(beanName, exposedObject);
        }

        return exposedObject;
    }

    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)){
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }

        return continueWithPropertyPopulation;
    }

    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean){
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (exposedObject == null){
                    return exposedObject;
                }
            }
        }

        return exposedObject;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).
                        postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (pvs != null){
                    for (PropertyValue propertyValue : pvs.getPropertyValues()){
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null){
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }

        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null){
                    return result;
                }
            }
        }

        return null;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {

        // ???singleton?????????Bean?????????????????????
        if (!beanDefinition.isSingleton()){
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof Aware){
            if (bean instanceof BeanFactoryAware){
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }

            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware)bean).setBeanClassLoader(getBeanClassLoader());
            }

            if (bean instanceof BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }
        }

        // ??????BeanPostProcessor Before??????
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean '" + beanName + "' failed", e);
        }

        // ??????BeanPostProcessor After??????
        return applyBeanPostProcessorsAfterInitialization(bean, beanName);
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof InitializingBean){
            ((InitializingBean)bean).afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (initMethod == null){
                throw new BeansException("Could not find an init method name '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            result = current != null ? current : result;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessAfterInitialization(result, beanName);
            result = current != null ? current : result;
        }
        return result;
    }

    /**
     *  Bean????????????
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
                    value = getBean(beanReference.getBeanName());
                } else {
                    Class<?> sourceType = value.getClass();
                    Class<?> tagetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService != null){
                        if (conversionService.canConvert(sourceType, tagetType)){
                            value = conversionService.convert(value, tagetType);
                        }
                    }
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e){
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    /**
     * ??????Bean??????????????????????????????????????????
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
     * ??????????????????????????????????????????
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