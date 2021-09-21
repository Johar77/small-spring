package com.johar.springframework.context.support;

import com.johar.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.johar.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @ClassName: AbstractXmlApplicationContext
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 17:14
 * @Since: 1.0.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}