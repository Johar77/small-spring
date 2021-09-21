package com.johar.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.beans.PropertyValue;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.config.BeanReference;
import com.johar.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.johar.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.johar.springframework.core.io.Resource;
import com.johar.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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
        } catch (IOException | ClassNotFoundException e) {
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

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++){
            if (!(childNodes.item(i) instanceof Element)){
                continue;
            }

            if (!"bean".equals(childNodes.item(i).getNodeName())){
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute(ID);
            String name = bean.getAttribute(NAME);
            String className = bean.getAttribute(CLASS);
            Class<?> clazz = Class.forName(className);

            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName[" + beanName + "]");
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++){
                if (!(bean.getChildNodes().item(j) instanceof Element)){
                    continue;
                }

                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())){
                    continue;
                }

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute(NAME);
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}