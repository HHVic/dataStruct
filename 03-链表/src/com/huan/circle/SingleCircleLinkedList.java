package com.huan.circle;

import com.huan.AbstractList;

import java.util.Objects;

/**
 * 单向循环链表
 * @param <E>
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);

        if (index == 0) {
            Node<E> newFirst = new Node<E>(element, first);
            Node<E> last = size == 0 ? first : node(size - 1);
            if(last != null){
                last.next = newFirst;
            }else{
                newFirst.next = newFirst;
            }

            first = newFirst;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
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

        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                Node<E> newFirst = first.next;
                last.next = newFirst;
                first = newFirst;
            }
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
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
    }

    /**
     * 获取index对应的node节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", data = [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            string.append(node.element);
            if (i != size - 1) {
                string.append("->");
            }
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
