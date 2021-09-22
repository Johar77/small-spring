package com.johar.springframework.beans;

import com.johar.springframework.beans.factory.BeanClassLoaderAware;
import com.johar.springframework.beans.factory.BeanFactory;
import com.johar.springframework.beans.factory.BeanFactoryAware;
import com.johar.springframework.beans.factory.BeanNameAware;
import com.johar.springframework.context.ApplicationContext;
import com.johar.springframework.context.ApplicationContextAware;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:33
 * @Since: 1.0.0
 */
public class UserService implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware {
    private String id;

    private String who;

    private int age;

    private Sex sex;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String who){
        this.who = who;
    }

    public UserService(String who, int age) {
        this.who = who;
        this.age = age;
    }

    public UserService(String who, Sex sex) {
        this.who = who;
        this.sex = sex;
    }

    public UserService(String who, int age, Sex sex) {
        this.who = who;
        this.age = age;
        this.sex = sex;
    }

    public String sayHello(String name){
        return "Hello, " + name + "!";
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "id='" + id + '\'' +
                ", who='" + who + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", userDao=" + userDao +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println(beanName);
    }

    @Override
    public void setApplicationContextAware(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}