package com.huan;

import java.util.Objects;

/**
 * 双向链表
 *
 * @param <E>
 */
public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            if(prev != null){
                string.append(prev.element);
            }
            string.append("_").append(element).append("_");
            if(next != null){
                string.append(next.element);
            }
            return string.toString();
        }
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);

        //往尾部插
        if(index == size){
            Node<E> newNode = new Node<>(last,element,null);
            //链表空
            if(index == 0){
                first = newNode;
            }else{
                last.next = newNode;
            }
            last = newNode;
        }else{
            Node<E> node = node(index);
            Node<E> prev = node.prev;
            Node<E> newNode = new Node<>(prev,element,node);

            //判断是否插入头
            if(prev == null){
                first = newNode;
            }else{
                prev.next = newNode;
            }
            node.prev = newNode;
        }
        ++size;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E e = node.element;
        node.element = element;
        return e;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E remove(int index) {
        rangCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        //判断是否插入头
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
        }

        //判断是否插入尾
        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
        }
        --size;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.element, element)) {
                return i;
            }
            node = node.next;
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 获取index对应的node节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangCheck(index);
        if(index <= (size >> 1)){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else{
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", data = [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            string.append(node);
            if (i != size - 1) {
                string.append(",");
            }
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
