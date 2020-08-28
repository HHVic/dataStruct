package com.huan.queue;

import com.huan.heap.BinaryHeap;
import com.huan.heap.Heap;

import java.util.Comparator;

/**
 * 使用二叉堆实现优先级队列
 * @param <E>
 */
public class PriorityQueue<E> implements Queue<E>{
    private Heap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public void enQueue(E element) {
        heap.add(element);
    }

    @Override
    public E deQueue() {
        return heap.remove();
    }

    @Override
    public E front() {
        return heap.get();
    }
}
