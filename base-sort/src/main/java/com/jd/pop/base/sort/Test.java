package com.jd.pop.base.sort;

public class Test {
    public static void main(String[] args) {
        //输入
        int[] arr = {8,7,5,3,2,8};
        int[] temp = {0,0,0,0,0,0};

        //1、冒泡排序：O(n^2)
//        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.bubbleSort(arr, arr.length);

        //2、选择排序：O(n^2)
//        SelectionSort selectionSort = new SelectionSort();
//        selectionSort.selectionSort(arr, arr.length);

        //3、插入排序：O(n^2)
//        InsertSort insertSort = new InsertSort();
//        insertSort.insertSort(arr, arr.length);

        //4、希尔排序：<O(n^2)
//        ShellSort shellSort = new ShellSort();
//        shellSort.shellSort(arr, arr.length);

        //5、快速排序：O(nlogn) 平均时间, O(n2) 最坏情况
//        QuickSort quickSort = new QuickSort();
//        quickSort.quickSort(arr, 0, arr.length-1);

        //6、归并排序：O(nlogn)
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr, 0, arr.length-1, temp);

        //打印输出
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
