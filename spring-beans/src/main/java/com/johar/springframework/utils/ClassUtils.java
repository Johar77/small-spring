package com.johar.springframework.utils;

/**
 * @ClassName: ClassUtils
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 23:41
 * @Since: 1.0.0
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex){

        }

        if (classLoader == null){
            classLoader = ClassUtils.class.getClassLoader();
        }

        return classLoader;
    }

    public static boolean isCglibProxyClass(Class<?> clazz){
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String classname){
        return (classname != null && classname.contains("$$"));
    }
}