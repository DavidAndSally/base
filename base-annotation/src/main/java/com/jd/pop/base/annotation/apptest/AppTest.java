package com.jd.pop.base.annotation.apptest;

import com.jd.pop.base.annotation.apptest.generatedClass.GeneratedClass;
import com.jd.pop.base.annotation.apptest.getter.GetterTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author wanjiadong
 * @description
 * @date Create in 15:34 2018/12/19
 */
@GetterTest
@GeneratedClass
public class AppTest {

    @GeneratedClass
    private String a;

    @GeneratedClass
    private String b;

    public AppTest(String a) {
        this.a = a;
    }

    @GeneratedClass
    public static void main(String[] args) {
        AppTest a = new AppTest("it works");

        System.out.println(a.getClass());

        try {
            Class clazz = Class.forName("com.wanjiadong.WanjiadongClass");
            Method method = clazz.getDeclaredMethod("getMessage");
            Object result = method.invoke(clazz.newInstance());
            System.out.println("result="+result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
