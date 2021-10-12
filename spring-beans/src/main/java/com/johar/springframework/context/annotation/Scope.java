package com.johar.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Scope
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 16:46
 * @Since: 1.0.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    String value() default "singleton";
}
