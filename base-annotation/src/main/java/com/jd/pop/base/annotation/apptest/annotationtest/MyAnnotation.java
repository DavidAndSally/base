package com.jd.pop.base.annotation.apptest.annotationtest;

import java.lang.annotation.*;

/**
 * 定义一个可以注解在Class,interface,enum上的注解
 *
 * @author wanjiadong
 * @description
 * @date Create in 17:02 2018/12/21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotation {

    /**
     * 定义注解的一个元素 并给定默认值
     * @return
     */
    String value() default "I am value";
}
