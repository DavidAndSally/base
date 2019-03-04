package com.jd.pop.base.annotation.practice1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
//    String desc();
//    String author();
//    int age() default 18;

    /**
     * 可以取其他的名字，必须以key=value的形式出现，如果用value取名就只需要加上value就行
     */
    String value();
}