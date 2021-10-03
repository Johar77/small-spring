package com.johar.springframework;

import com.johar.springframework.beans.*;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.johar.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.johar.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultListableBeanFactoryTest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:34
 * @Since: 1.0.0
 */
public class DefaultListableBeanFactoryTest {

    @Test
    public void test_Constructor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.sayHello("Johar"));
    }

    @Test
    public void test_Constructor_1(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", new Object[]{ "Anna", Sex.FEMALE });
        System.out.println(userService.sayHello("Johar"));
        System.out.println(userService);
    }

    @Test
    public void test_Property(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        UserDao userDao = (UserDao)beanFactory.getBean("userDao");
        System.out.println(userDao);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("who", "Anna"));
        propertyValues.addPropertyValue(new PropertyValue("age", 21));
        propertyValues.addPropertyValue(new PropertyValue("sex", Sex.FEMALE));
        propertyValues.addPropertyValue(new PropertyValue("userDao", userDao));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", new Object[]{ "Anna", Sex.FEMALE });
        System.out.println(userService.sayHello("Johar"));
        System.out.println(userService);

    }

    @Test
    public void test_xml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.sayHello("Johar"));
        System.out.println(userService);
    }

    @Test
    public void test_applicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.sayHello("Johar"));
        System.out.println(userService);
    }

    @Test
    public void test_prototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        System.out.println(userService1);
        System.out.println(userService2);

        System.out.println(userService1.getCompany().getPosition("1"));
    }

    @Test
    public void test_double2(){
        int num = 37;
        System.out.println(Integer.toBinaryString(num));
        num |= num >>> 1;
        System.out.println(Integer.toBinaryString(num));
        num |= num >>> 2;
        System.out.println(Integer.toBinaryString(num));
        num |= num >>> 4;
        System.out.println(Integer.toBinaryString(num));
        num |= num >>> 8;
        System.out.println(Integer.toBinaryString(num));
        num |= num >>> 16;
        System.out.println(Integer.toBinaryString(num));

        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }
}