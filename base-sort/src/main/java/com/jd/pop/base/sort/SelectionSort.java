package com.jd.pop.base.sort;

/**
 * 选择排序 基本思想：每一趟在n-i+1(i=1,2,...,n-1)个记录中选取关键字最小的记录作为有序序列中第i个记录。
 *
 * @author qiwei11
 * @date 2019/2/12
 */
public class SelectionSort {

    public void selectionSort(int arr[], int length) {
        for (int i = 0; i < length; i++) {
            int index = i;
            //选出最小值
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            //最小值放到最左侧，依此类推
            if (index == i) {
                continue;
            } else {
                int temp;
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
