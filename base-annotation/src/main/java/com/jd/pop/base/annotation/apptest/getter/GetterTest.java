package com.jd.pop.base.annotation.apptest.getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wanjiadong
 * @description
 * @date Create in 15:20 2018/12/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface GetterTest {
}
