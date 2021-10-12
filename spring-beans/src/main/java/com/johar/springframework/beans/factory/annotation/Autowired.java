package com.johar.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Autowried
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 22:37
 * @Since: 1.0.0
 */
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
