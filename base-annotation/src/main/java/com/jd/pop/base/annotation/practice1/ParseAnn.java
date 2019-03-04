package com.jd.pop.base.annotation.practice1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnn {

    public static void main(String[] args) {
        try {
            // 使用类加载器加载类
            Class c = Class.forName("com.jd.pop.base.annotation.practice1.Child");
            // 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            // 上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
            if (isExist) {
                // 拿到注解实例，解析类上面的注解
                Description d = (Description) c.getAnnotation(Description.class);
                System.out.println(d.value());
            }

            //获取所有的方法
            Method[] ms = c.getDeclaredMethods();
            // 遍历所有的方法
//            for (Method m : ms) {
//                boolean isExist1 = m.isAnnotationPresent(Description.class);
//                if (isExist1) {
//                    Description d1 = m.getAnnotation(Description.class);
//                    System.out.println(d1.value());
//                }
//            }

            //另一种解析方法
            for (Method m : ms) {
                //拿到方法上的所有的注解
                Annotation[] as=m.getAnnotations();
                for (Annotation a : as) {
                    //用二元操作符判断a是否是Description的实例
                    if (a instanceof Description) {
                        Description d=(Description) a;
                        System.out.println(d.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}