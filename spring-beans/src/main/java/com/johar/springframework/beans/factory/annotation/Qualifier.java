package com.johar.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Qualifier
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 22:39
 * @Since: 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface Qualifier {
    String value() default "";
}
