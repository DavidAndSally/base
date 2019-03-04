package com.jd.pop.base.sort;

/**
 * 冒泡排序 基本思想：先从数组中找到最大值(或最小值)并放到数组最左端(或最右端)，然后在剩下的数字中找到次大值(或次小值)，以此类推，直到数组有序排列。
 *
 * @author qiwei11
 * @date 2019/2/12
 */
public class BubbleSort {

    public void bubbleSort(int arr[], int length) {
        //两两比较直到选出最大，依次循环
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
