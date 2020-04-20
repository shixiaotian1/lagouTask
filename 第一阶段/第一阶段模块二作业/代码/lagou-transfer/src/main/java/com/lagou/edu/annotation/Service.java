package com.lagou.edu.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解service
 */
@Target(value= {ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
