package com.johar.springframework.beans.factory.config;

import com.johar.springframework.beans.factory.HierarchicalBeanFactory;
import com.johar.springframework.utils.StringValueResolver;

/**
 * @ClassName: ConfigurableBeanFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 13:41
 * @Since: 1.0.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}
