package com.huan.tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {
    //比较器
    protected Comparator<E> comparator;

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BST() {
        this(null);
    }

    /**
     * 添加节点
     *
     * @param element
     */
    public void add(E element) {
        // 添加根节点
        if (root == null) {
            root = createNode(element,null);
            afterAdd(root);
        } else {
            // 添加非根节点
            Node<E> node = root;
            Node<E> parent = root;
            int temp = 0;
            while (node != null) {
                temp = compare(element, node.element);
                if (temp == 0) {
                    node.element = element;
                    return;
                }
                parent = node;
                node = temp > 0 ? node.right : node.left;
            }
            Node<E> newNode = createNode(element,parent);
            if (temp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
            afterAdd(newNode);
        }
        ++size;
    }

    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element, parent);
    }

    /**
     * 添加后的处理逻辑，留给子类具体实现
     * @param node
     */
    protected void afterAdd(Node<E> node){}

    /**
     * 删除元素对应的节点
     *
     * @param element
     */
    public void remove(E element) {
        //调用重载方法
        remove(node(element));
    }

    /**
     * 删除节点
     *
     * @param node
     */
    private void remove(Node<E> node) {
        //删除节点
        // 没找到要删除的节点直接返回
        if(node == null) return ;
        //先处理度为二的节点,这里使用前驱去替换节点(前驱和后继节点都必须是度为0或者度为1的节点)
        if (node.hasTwoChildren()) {
            Node<E> prev = precessor(node);
            node.element = prev.element;
            node = prev;
        }
        //node必定指向度为0或者度为1的节点，统一处理
        if (node.isLeaf()) {
            //node是叶子节点，判断是左还是右
            if (node.parent == null) {
                root = null;
            } else if (node.isLeftChild()) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        } else {
            //node为度为一的节点  node.parent.left(right) = node.left(right)
            Node<E> child = null;
            if (node.left != null) {
                child = node.left;
            } else {
                child = node.right;
            }
            child.parent = node.parent;
            if (node.parent != null) {
                if (node.isLeftChild()) {
                    node.parent.left = child;
                } else {
                    node.parent.right = child;
                }
            } else {
                root = child;
            }
            afterRemove(node);
        }
        --size;
    }

    protected void afterRemove(Node<E> node){}

    /**
     * 根据元素获得该节点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            if (compare(element, node.element) > 0) {
                //传入的元素大于当前节点，往右找
                node = node.right;
            } else if (compare(element, node.element) < 0) {
                //传入的元素小于当前节点，往左找
                node = node.left;
            } else {
                //传入的元素等于当前节点，返回该节点
                return node;
            }
        }
        return null;
    }

    /**
     * 是否包含某个节点
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 非空校验
     *
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
    }

    /**
     * 比较两个元素
     *
     * @param e1
     * @param e2
     * @return temp < 0 : e1 < e2  temp > 0 e1 < e2  temp == 0  e1 = e2
     */
    private int compare(E e1, E e2) {
        // 传递了比较器
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public Node<E> precessor(E element) {
        return precessor(node(element));
    }

    public Node<E> successor(E element) {
        return successor(node(element));
    }
}
