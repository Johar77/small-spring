package com.johar.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Value
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 22:41
 * @Since: 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value();
}
