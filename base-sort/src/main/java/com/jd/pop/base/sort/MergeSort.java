package com.jd.pop.base.sort;

/**
 * 二路归并排序 基本思想：“归并”的含义是将两个或两个以上的有序序列组合成一个新的有序表。假设初始序列含有n个记录，则可以看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，得到（表示不小于x的最小整数）个长度为2(或者是1)的有序子序列，再两两归并。如此重复，直到得到一个长度为n的有序序列为止。这种排序方法称为2-路归并排序。
 *
 * @author qiwei11
 * @date 2019/2/13
 */
public class MergeSort {

    // 归并排序
    public void mergeSort(int arr[], int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid, temp);
        mergeSort(arr, mid + 1, end, temp);

        // 合并两个有序序列
        // 表示辅助空间有多少个元素
        int length = 0;
        int i_start = start;
        int i_end = mid;
        int j_start = mid + 1;
        int j_end = end;
        while (i_start <= i_end && j_start <= j_end) {
            if (arr[i_start] < arr[j_start]) {
                temp[length] = arr[i_start];
                length++;
                i_start++;
            } else {
                temp[length] = arr[j_start];
                length++;
                j_start++;
            }
        }
        while (i_start <= i_end) {
            temp[length] = arr[i_start];
            i_start++;
            length++;
        }
        while (j_start <= j_end) {
            temp[length] = arr[j_start];
            length++;
            j_start++;
        }
        // 把辅助空间的数据放到原空间
        for (int i = 0; i < length; i++) {
            arr[start + i] = temp[i];
        }
    }

}
