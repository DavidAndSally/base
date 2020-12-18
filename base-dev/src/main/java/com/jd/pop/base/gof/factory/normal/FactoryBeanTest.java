package com.jd.pop.base.gof.factory.normal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        Stu s1 = ctx.getBean("stuOne", Stu.class);
        Stu s2 = ctx.getBean("stuOne", Stu.class);
        System.out.println(s1 == s2);
        Stu s3 = ctx.getBean("stuTwo", Stu.class);
        System.out.println(s1 == s3);

    }
}
