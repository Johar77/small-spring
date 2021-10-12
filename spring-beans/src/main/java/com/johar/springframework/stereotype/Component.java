package com.johar.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @ClassName: Component
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 16:48
 * @Since: 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    String value() default "";
}
