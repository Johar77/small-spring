package com.johar.springframework.test.reference;

import com.johar.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: CircleTest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:50
 * @Since: 1.0.0
 */

public class CircleTest {

    @Test
    public void test_circular(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-circle.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);

        System.out.println(husband.queryWife());
        System.out.println(wife.queryHusband());
    }
}