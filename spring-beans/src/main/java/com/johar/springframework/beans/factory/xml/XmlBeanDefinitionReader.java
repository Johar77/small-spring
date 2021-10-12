package com.johar.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.PropertyValue;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.config.BeanReference;
import com.johar.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.johar.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.johar.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.johar.springframework.core.io.Resource;
import com.johar.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: XmlBeanDefinitionReader
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:45
 * @Since: 1.0.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    private final static String BEAN = "bean";
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String CLASS = "class";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try(InputStream inputStream = resource.getInputStream()){
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations){
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        Element componentScan = root.element("component-scan");
        if (componentScan != null){
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)){
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList){
            String id = bean.attributeValue(ID);
            String name = bean.attributeValue(NAME);
            String className = bean.attributeValue(CLASS);
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            Class<?> clazz = Class.forName(className);

            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName[" + beanName + "]");
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            if (StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList){
                String attrName = property.attributeValue(NAME);
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");

                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}