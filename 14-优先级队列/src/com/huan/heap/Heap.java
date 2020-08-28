package com.huan.heap;

public interface Heap<E> {
    /**
     * 堆的大小
     * @return
     */
    int size();

    /**
     * 堆是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空堆
     */
    void clear();

    /**
     * 向堆中添加元素
     * @param element
     */
    void add(E element);

    /**
     * 获取堆顶元素
     * @return
     */
    E get();

    /**
     * 删除堆顶元素
     * @return
     */
    E remove();

    /**
     * 替换堆顶元素
     * @param element
     * @return
     */
    E replace(E element);
}
