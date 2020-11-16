package com.jd.pop.base.arralist.basic;

public class Main {
    public static void main(String[] args) {
//        int[] array = new int[10];
//        array[0] = 1;

        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2,19);
        System.out.println(list.toString());
    }
}
