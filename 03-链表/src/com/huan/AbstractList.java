package com.huan;

public abstract class AbstractList<E> implements List<E>{
    protected int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public void add(E element) {
        add(size,element);
    }

    /**
     * 检查数组索引
     * @param index
     */
    protected void rangCheck(int index){
        if(index < 0 || index >= size){
            outOfBound(index);
        }
    }

    /**
     * 检查添加数组索引
     * @param index
     */
    protected void rangCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBound(index);
        }
    }

    /**
     * 数组越界处理
     * @param index
     */
    protected void outOfBound(int index){
        throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
    }
}
