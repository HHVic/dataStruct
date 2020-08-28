package com.huan.queue;

public interface Queue<E> {
    /**
     * 元素数量
     * @return
     */
    int size();

    /**
     * 队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空队列
     */
    void clear();

    /**
     * 队尾入队
     * @param element
     */
    void enQueue(E element);

    /**
     * 队头出队
     * @return
     */
    E deQueue();

    /**
     * 获取队头
     * @return
     */
    E front();
}
