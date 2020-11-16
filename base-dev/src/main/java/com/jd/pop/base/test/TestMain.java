package com.jd.pop.base.test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void intersection(int length1, int length2) {
        List<Integer> list1 = Lists.newLinkedList();
        for (int i = 0; i < length1; i++) {
            list1.add(i);
        }
        List<Integer> list2 = Lists.newLinkedList();
        for (int i = 0; i < length2; i++) {
            list2.add(i);
        }
        long start = System.currentTimeMillis();
        list1.retainAll(list2);
        long end = System.currentTimeMillis();
        System.out.println("list.retainAll   消耗时间(数量：" + length1 + ")：" + (end - start));
        long start1 = System.currentTimeMillis();
        Sets.intersection(ImmutableSet.copyOf(list1), ImmutableSet.copyOf(list2));
        long end1 = System.currentTimeMillis();
        System.out.println("Sets.intersection消耗时间(数量：" + length2 + ")：" + (end1 - start1));
    }

    public static void main(String[] args) {
        int[] array = {10, 100, 1000, 5000, 10000, 50000, 100000};
        for (int i = 0; i < array.length; i++) {
            intersection(array[i], array[i]);
        }
        System.out.println("两个结果集不一样--------------------------");
//        for (int i = 0; i < array.length; i++) {
//            intersection(array[i], array[i] / 10);
//        }
//        System.out.println("两个结果集不一样--------------------------");
//        for (int i = 0; i < array.length; i++) {
//            intersection(array[i] / 10, array[i]);
//        }
    }
}