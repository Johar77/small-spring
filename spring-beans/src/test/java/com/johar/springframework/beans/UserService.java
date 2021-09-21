package com.johar.springframework.beans;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 17:33
 * @Since: 1.0.0
 */
public class UserService {

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

    @Override
    public String toString() {
        return "UserService{" +
                "who='" + who + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", userDao=" + userDao +
                '}';
    }
}