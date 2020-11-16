package com.jd.pop.base.arralist.basic;

/**
 * 手写arrayList
 *
 * @author qiw-a
 * @date 2020/10/30
 */
public class MyArrayList {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    public int[] elements;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        elements = new int[capacity];
    }

    public void add(int element) {
        //扩容
//        this.elements[size++] = element;
//        size++;
        add(size, element);
    }

    public void add(int index, int element) {
        if (index < 0 && index >= elements.length) {
            throw new IndexOutOfBoundsException();
        }

        capacityCheck();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public void capacityCheck() {
        if (size < elements.length) {
            return;
        }
        int newCapacity = elements.length + elements.length >> 1;
        int[] newElements = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    public int remove(int index) {
        return 0;
    }

    public int set(int index, int element) {
        return 0;
    }

    public int get(int index) {
        return 0;
    }

    public void clear() {
    }

    public void capacityCheck(int capacity) {

    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("size=").append(size).append("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                sb.append(elements[i]);
            } else {
                sb.append(elements[i]).append(";");
            }
        }
        sb.append("]");
        return sb.toString();

//        return "MyArrayList{" +
//            "size=" + size +
//            ", elements=" + Arrays.toString(elements) +
//            '}';
    }
}
