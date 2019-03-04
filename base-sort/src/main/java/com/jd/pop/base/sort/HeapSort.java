package com.jd.pop.base.sort;

/**
 * 堆排序 基本思想：
 *
 * @author qiwei11
 * @date 2019/2/13
 */
public class HeapSort {

    /*
        @param arr 待调整的数组
        @param i 待调整的结点的下标
        @param length 数组的长度
    */
    public void HeapAdjust(int arr[], int i, int length) {
        // 调整i位置的结点
        // 先保存当前结点的下标
        int max = i;
        // 当前结点左右孩子结点的下标
        int lchild = i * 2 + 1;
        int rchild = i * 2 + 2;
        if (lchild < length && arr[lchild] > arr[max]) {
            max = lchild;
        }
        if (rchild < length && arr[rchild] > arr[max]) {
            max = rchild;
        }
        // 若i处的值比其左右孩子结点的值小，就将其和最大值进行交换
        if (max != i) {
            int temp;
            temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            // 递归
            HeapAdjust(arr, max, length);
        }
    }

    // 堆排序
    public void HeapSort(int arr[], int length) {
        // 初始化堆
        // length / 2 - 1是二叉树中最后一个非叶子结点的序号
        for (int i = length / 2 - 1; i >= 0; i--) {
            HeapAdjust(arr, i, length);
        }
        // 交换堆顶元素和最后一个元素
        for (int i = length - 1; i >= 0; i--) {
            int temp;
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            HeapAdjust(arr, 0, i);
        }
    }
}
