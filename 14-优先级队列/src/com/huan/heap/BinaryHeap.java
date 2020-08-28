package com.huan.heap;


import java.util.Comparator;

/**
 * 二叉堆（大顶堆）
 *
 * @param <E>
 */
public class BinaryHeap<E> extends AbstractHeap<E> {
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if(elements != null && elements.length > 0){
            int length = Math.max(DEFAULT_CAPACITY,elements.length);
            this.elements = (E[]) new Object[length];
            System.arraycopy(elements,0,this.elements,0,length);
            size = length;
            heapify();
        }else{
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements,null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null,comparator);
    }

    public BinaryHeap() {
        this(null,null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E e) {
        elementNotNullCheck(e);
        ensureCapacity();
        elements[size ++] = e;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        E root = get();
        elements[0] = elements[--size];
        elements[size] = null;
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = get();
        elements[0] = element;
        siftDown(0);
        return root;
    }

    /**
     * 批量建堆
     */
    private void heapify(){
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void emptyCheck(){
        if(size == 0){
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementNotNullCheck(E e){
        if(e == null){
            throw new IllegalArgumentException("element not be null");
        }
    }

    private void ensureCapacity(){
        if(size <= elements.length) return ;
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElement = (E[]) new Object[newCapacity];
        // 拷贝
        System.arraycopy(elements,0,newElement,0,oldCapacity);
        elements = newElement;
    }

    private void siftDown(int index){
        E e = elements[index];
        while(index < (size >> 1)){
            // 默认左边最大
            int leftIndex = (index << 1) + 1;
            E child = elements[leftIndex];
            int rightIndex;
            //如果右边存在并且右边比左边大
            if((rightIndex = leftIndex + 1) < size && compare(elements[rightIndex],child) > 0){
                child = elements[leftIndex = rightIndex];
            }
            if(compare(e,child) >= 0) break;
            elements[index] = child;
            index = leftIndex;
        }
        elements[index] = e;
    }

    private void siftUp(int index){
        E e = elements[index];
        while(index  > 0){
            int pindex = (index - 1) >> 1;
            if(compare(e, elements[pindex]) <= 0){
                break;
            }
            elements[index] = elements[pindex];
            index = pindex;
        }
        elements[index] = e;
    }
}
