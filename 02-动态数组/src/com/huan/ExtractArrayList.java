package com.huan;

import java.util.Objects;

public class ExtractArrayList<E> implements List<E> {

    //数组元素个数
    private int size;
    private E[] data;
    private int first;
    //数组大小
    private int length;

    //默认容量
    private static final int DEFAULT_CAPACITY = 10;

    private static final int ELEMENT_NOT_FOUND = -1;

    public ExtractArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ExtractArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        data = (E[]) new Object[capacity];
        this.first = -1;
        this.length = capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        ensureCapacity(size + 1);

        //数组为空  size == 0
        if (first == -1) {
            first = 0;
        }
        //往头添加
        index = realIndex(index);
        if (index == first && size > 1) {
            index = (length + first - 1) % length;
            first = index;
        }
        if (index != size) {
            if (index > first) {
                int right = first + size - 1;
                int mid = (first + (right - first) >> 1);
                if (index > mid) {
                    System.arraycopy(data, index, data, index + 1, right - index + 1);
                } else {
                    if (first == 0) {
                        index = (length + first - 1) % length;

                    } else {
                        System.arraycopy(data, first, data, first - 1, index - first + 1);
                    }
                    first = first - 1;
                }
            }
            if (index < first) {
                int right = (first + size - 1) % length;
                int lrange = index + length - first;
                int rrange = right - index;
                if (lrange > rrange) {
                    System.arraycopy(data, index, data, index + 1, right - index + 1);
                } else {
                    //将后面的元素前移
                    System.arraycopy(data, first, data, first - 1, length - first);
                    data[length - 1] = data[0];
                    if (index > 0) {
                        System.arraycopy(data, 1, data, 0, --index);
                    }
                    first = first - 1;
                }
            }
        }
        data[index] = element;
        ++size;
    }

    @Override
    public E set(int index, E element) {
        //rangCheck(index);
        int realIndex = realIndex(index);
        E oldElement = data[realIndex];
        data[realIndex] = element;
        return oldElement;
    }

    @Override
    public E get(int index) {
        //rangCheck(index);
        int realIndex = realIndex(index);
        return data[realIndex];
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public E remove(int index) {
        rangCheck(index);
        int realIndex = realIndex(index);
        E oldElement = data[realIndex];
        if (index == first && size > 1) {
            ++first;
        }
        //System.arraycopy(data, index + 1, data, index, size - index);
        data[realIndex] = null;
        --size;
        return oldElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
        first = -1;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), element)) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", data = [");

        for (int i = 0; i < size; i++) {
            string.append(get(i));
            if (i != size - 1) {
                string.append(",");
            }
        }
        string.append("]");
        string.append(", first = ").append(first);
        return string.toString();
    }

    /**
     * 检查数组索引
     *
     * @param index
     */
    private void rangCheck(int index) {
        if (index < 0 || index >= length) {
            outOfBound(index);
        }
    }

    /**
     * 检查添加数组索引
     *
     * @param index
     */
    private void rangCheckForAdd(int index) {
        if (index < 0 || index > length) {
            outOfBound(index);
        }
    }

    /**
     * 数组越界处理
     *
     * @param index
     */
    private void outOfBound(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
    }

    /**
     * 动态扩容(原本1.5倍)
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        if (capacity >= data.length) {
            //新容量扩容为原来的1.5倍
            int newCapacity = capacity + (capacity >> 1);
            //申请新的数组
            E[] newData = (E[]) new Object[newCapacity];
            //原数组值拷贝到新数组
            System.arraycopy(data, 0, newData, 0, size);
            //数组指向新数组
            data = newData;
        }
    }

    /**
     * 得到数组中对应元素真实索引
     *
     * @param index
     * @return
     */
    private int realIndex(int index) {
        rangCheck(index);
        return (first + index) % length;
    }
}
