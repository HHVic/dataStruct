package com.huan;

import java.util.Objects;

public class ArrayList<E> extends AbstractList<E> {


    private E[] data;

    //默认容量
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity){
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        data = (E[]) new Object[capacity];
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        ensureCapacity(size + 1);
        if(index != size) {
            System.arraycopy(data, index, data, index + 1, size - index + 1);
        }
        data[index] = element;
        ++size;
    }

    @Override
    public E set(int index, E element) {
        rangCheck(index);
        E oldElement = data[index];
        data[index] = element;
        return oldElement;
    }

    @Override
    public E get(int index) {
        rangCheck(index);
        return data[index];
    }

    @Override
    public E remove(int index) {
        rangCheck(index);
        E oldElement = data[index];
        System.arraycopy(data,index + 1,data,index,size - index);
        data[--size] = null;
        return oldElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if(Objects.equals(data[i],element)){
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
            string.append(data[i]);
            if(i != size - 1){
                string.append(",");
            }
        }
        string.append("]");
        return string.toString();
    }

    /**
     * 动态扩容(原本1.5倍)
     * @param capacity
     */
    private void ensureCapacity(int capacity){
        if(capacity >= data.length){
            //新容量扩容为原来的1.5倍
            int newCapacity = capacity + (capacity >> 1);
            //申请新的数组
            E[] newData = (E[]) new Object[newCapacity];
            //原数组值拷贝到新数组
            System.arraycopy(data,0,newData,0,size);
            //数组指向新数组
            data = newData;
        }
    }
}
