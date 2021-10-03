package com.johar.springframework;

import com.johar.springframework.aop.AdvisedSupport;
import com.johar.springframework.aop.TargetSource;
import com.johar.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.johar.springframework.aop.framework.Cglib2AopProxy;
import com.johar.springframework.aop.framework.JdkDynamicAopProxy;
import com.johar.springframework.beans.UserService;
import com.johar.springframework.dao.Company;
import com.johar.springframework.dao.IUserService;
import com.johar.springframework.interceptor.UserServiceInterceptor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: AopTest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/23 17:01
 * @Since: 1.0.0
 */
public class AopTest {

    @Test
    public void test_proxy_class(){
        Company company = (Company) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Company.class}, ((proxy, method, args) -> "你被代理了！！！"));
        System.out.println(company.getPosition("1"));
        System.out.println(Integer.toHexString(-1));
        System.out.println(Integer.toHexString( -1 << 29));
        //Executors.newCachedThreadPool()
    }

    @Test
    public void test_proxy_method(){
        Object targetObj = new UserService();


    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(
                "execution(* com.johar.springframework.beans.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method[] methods = clazz.getDeclaredMethods();
        Method method = clazz.getDeclaredMethod("sayHello", String.class);

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic(){
        IUserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.johar.springframework.dao.IUserService.*(..))"));

        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("result: " + proxy_jdk.sayHello("Johar"));

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("result: " + proxy_cglib.sayHello("Anna"));
    }
}