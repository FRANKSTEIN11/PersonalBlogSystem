package com.example.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yenanren
 * @date 2022/4/11 0011
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckWord {

    /**
     * 权限字符串
     * @return
     */
    String value() default "";
}
