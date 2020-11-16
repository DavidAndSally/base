package com.jd.pop.base.gof;

import java.util.ArrayList;

public class TestList {
    public static void main(String[] args) {
        ArrayList<Float> list_str = new ArrayList<Float>();
        int len = (int) Math.pow(2, 32);
        System.out.println("运行次数：" + len);
        for (int i = 0; i < len; i++) {
            //String str="a "+i;
            //list_str.add(str);
//          int t=i;
//          Student student=new Student();
//          student.setId(i);
//          student.setName("第+"+i);
//          list_str.add(student);
            float f = (float) i;
            list_str.add(f);
        }
        for (int j = 0; j < len; j++) {
            System.out.println(list_str.get(j) + "    " + j);
        }
        System.out.println("运行结束！" + len);
    }
}
