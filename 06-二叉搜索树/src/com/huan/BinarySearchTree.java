package com.huan;

import com.huan.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;
    private Node<E> root;
    //比较器
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    @Override
    public Object root() {
        return (Node<E>) root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node);
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        private Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_p(");
            String ss = parent == null ? "null" : parent.element.toString();
            sb.append(ss);
            sb.append(")");
            return sb.toString();
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean hasTwoChildren() {
            return left != null && right != null;
        }

        private boolean isLeftChild() {
            return parent != null && this == this.parent.left;
        }

        private boolean isRightChild() {
            return parent != null && this == this.parent.right;
        }
    }

    /**
     * 获取树的节点个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空树
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 添加节点
     *
     * @param element
     */
    public void add(E element) {
        // 添加根节点
        if (root == null) {
            root = new Node<>(element, null);
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
            Node<E> newNode = new Node<>(element, parent);
            if (temp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        ++size;
    }

    public Node<E> precessor(E element) {
        return precessor(node(element));
    }

    public Node<E> successor(E element) {
        return successor(node(element));
    }

    /**
     * 找node的前驱节点
     *
     * @param node
     * @return node的前驱节点
     */
    private Node<E> precessor(Node<E> node) {
        if (node == null) return node;
        // 左不为空 前驱为左子树的最右边的节点  node.left.right.right.right....
        if (node.left != null) {
            Node<E> left = node.left;
            while (left.right != null) {
                left = left.right;
            }
            return left;
        }
        // 左为空 node的父节点不空 前驱为node的父节点第一个出现右子树  node.parent.parent   node == node.parent.right
        while (node.parent != null) {
            if (node == node.parent.right) {
                return node.parent;
            }
            node = node.parent;
        }
        // 左为空 node的父节点也为空 无前驱
        return null;
    }

    /**
     * 找node的后继节点
     *
     * @param node
     * @return node的后继
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) return node;
        // 右不为空 前驱为右子树的最左边的节点  node.right.left.left.left....
        if (node.right != null) {
            Node<E> right = node.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
        // 右为空 node的父节点不空 前驱为node的父节点第一个出现左子树  node.parent.parent   node == node.parent.left
        while (node.parent != null) {
            if (node == node.parent.left) {
                return node.parent;
            }
            node = node.parent;
        }
        // 右为空 node的父节点也为空 无前驱
        return null;
    }

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
        //先处理度为二的节点,这里使用前驱去替换节点(前驱和后继节点都必须是度为0或者度为1的节点)
        if (node.hasTwoChildren()) {
            Node<E> prev = precessor(node);
            node.element = prev.element;
            node = prev;
        }
        //node必定指向度为0或者度为1的节点，统一处理
        if (node.isLeaf()) {
            //node是叶子节点，判断是左还是右
            if (node.isLeftChild()) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }else{
            //node为度为一的节点  node.parent.left(right) = node.left(right)
            if(node.parent != null){
                Node<E> child = null;
                if(node.left != null){
                    child = node.left;
                }else{
                    child = node.right;
                }
                child.parent = node.parent;
                if (node.isLeftChild()) {
                    node.parent.left = child;
                } else {
                    node.parent.right = child;
                }
            }
        }
    }

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

    /**
     * 先序遍历
     *
     * @param visitor 访问操作，用户自定义
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderNoneRe(root, visitor);
    }

    /**
     * 先序遍历（递归）
     *
     * @param node
     * @param visitor
     */
    public void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 先序遍历（非递归）
     *
     * @param root
     * @param visitor
     */
    public void preorderNoneRe(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Node<E> p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
            while (p == null && !stack.isEmpty()) {
                p = stack.pop();
                visitor.visit(p.element);
                p = p.right;
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param visitor 访问操作，用户自定义
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderNoneRe(root, visitor);
    }

    /**
     * 中序遍历（非递归）
     *
     * @param root
     * @param visitor
     */
    public void inorderNoneRe(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Node<E> p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
            while (p == null && !stack.isEmpty()) {
                p = stack.pop();
                if (visitor.visit(p.element)) {
                    return;
                }
                p = p.right;
            }
        }
    }

    /**
     * 中序遍历（递归）
     *
     * @param node
     * @param visitor
     */
    public void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     *
     * @param visitor 访问操作，用户自定义
     */
    public void postorder(Visitor<E> visitor) {
        if (visitor == null) return;
        postorder(root, visitor);
    }

    /**
     * 后序遍历（递归）
     *
     * @param node
     * @param visitor
     */
    public void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     *
     * @param visitor 访问操作，用户自定义
     */
    public void levelOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        levelOrder(root, visitor);
    }

    /**
     * 层序遍历
     *
     * @param root
     * @param visitor
     */
    public void levelOrder(Node<E> root, Visitor<E> visitor) {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            boolean stop = visitor.visit(node.element);
            if (stop) return;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 不传Visitor遍历使用默认实现打印
     */
    public void order() {
        order(new DefaultVisitor<>());
    }

    /**
     * 不传遍历方式默认使用先序遍历
     *
     * @param visitor
     */
    public void order(Visitor<E> visitor) {
        order(visitor, OrderType.PREORDER);
    }

    /**
     * 只传递遍历方式
     *
     * @param orderType
     */
    public void order(OrderType orderType) {
        order(new DefaultVisitor<>(), orderType);
    }

    /**
     * @param visitor
     * @param orderType
     */
    public void order(Visitor<E> visitor, OrderType orderType) {
        switch (orderType) {
            case PREORDER:
                preorder(visitor);
                break;
            case INORDER:
                inorder(visitor);
                break;
            case POSTORDER:
                postorder(visitor);
                break;
            case LEVEL_ORDER:
                levelOrder(visitor);
                break;
        }
    }

    /**
     * @return 树的高度
     */
    public int height() {
        return heightNoneR(root);
    }

    /**
     * 获取树的高度（递归）
     *
     * @param node
     * @return
     */
    public int height(Node<E> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 获取树的高度（非递归递归）层序遍历
     *
     * @param root
     * @return
     */
    public int heightNoneR(Node<E> root) {
        if (root == null) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        //记录队列中有多少个元素
        int size = 1;
        int height = 0;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            --size;
            //.....
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            //如果当前size == 0，代表一层访问完毕
            if (size == 0) {
                //....
                ++height;
                size = queue.size();
            }
        }
        return height;
    }

    /**
     * 判断是否是完全二叉树
     *
     * @param root
     * @return
     */
    public boolean isComplete(Node<E> root) {
        if (root == null) return true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            //....后面必须是叶子，但是node不是叶子，不是完全二叉树
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else {
                //左空右不空不是完全二叉树
                if (node.right != null) {
                    return false;
                }
                // 左空 右空  后面遍历必须是叶子后面处理
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 右空，不管左空或者不空后面都必须是叶子
                leaf = true;
            }
        }
        return true;
    }


    public enum OrderType {
        PREORDER,
        INORDER,
        POSTORDER,
        LEVEL_ORDER,
    }
}
