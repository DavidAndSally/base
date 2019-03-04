package com.jd.pop.base.sort;

/**
 * 插入排序 基本思想:将无序序列插入到有序序列中。
 *
 * @author qiwei11
 * @date 2019/2/12
 */
public class InsertSort {

    public void insertSort(int arr[], int length) {
        for (int i = 1; i < length; i++) {
            int j;
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }
}
