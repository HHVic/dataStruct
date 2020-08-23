package com.huan;

public interface List<E> {

    static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 判断数组是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 数组的长度
     * @return
     */
    int size();

    /**
     * 数组末尾追加元素
     * @param element
     */
    void add(E element);

    /**
     * 在指定位置新增元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 设定某个位置的值
     * @param index
     * @param element
     * @return 旧的元素
     */
    E set(int index, E element);

    /**
     * 获取某个位置的值
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 数组中是否包含某个元素
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 删除元素
     * @param index
     * @return 删除的元素
     */
    E remove(int index);

    /**
     * 获取元素出现的第一个索引
     * @param element
     * @return
     */
    int indexOf(E element);

    /**
     * 清空数组
     */
    void clear();

}
