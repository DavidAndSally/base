package com.jd.pop.base.sort;

/**
 * 希尔排序 基本思想：先将待排记录序列分割成为若干子序列分别进行插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行一次直接插入排序。
 *
 * @author qiwei11
 * @date 2019/2/12
 */
public class ShellSort {

    public void shellSort(int arr[], int length) {
        int increasement = length;
        int i, j, k;
        do {
            // 确定分组的增量
            increasement = increasement / 3 + 1;
            for (i = 0; i < increasement; i++) {
                for (j = i + increasement; j < length; j += increasement) {
                    if (arr[j] < arr[j - increasement]) {
                        int temp = arr[j];
                        for (k = j - increasement; k >= 0 && temp < arr[k]; k -= increasement) {
                            arr[k + increasement] = arr[k];
                        }
                        arr[k + increasement] = temp;
                    }
                }
            }
        } while (increasement > 1);
    }
}
