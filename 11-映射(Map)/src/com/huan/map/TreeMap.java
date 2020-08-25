package com.huan.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 红黑树实现TreeMap
 *
 * @param <K>
 * @param <V>
 */
public class TreeMap<K, V> implements Map<K, V> {

    private int size;
    private Node<K, V> root;
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    //比较器
    private Comparator<K> comparator;

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {
        this(null);
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
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        // 添加根节点
        if (root == null) {
            root = new Node<>(key, value, null);
            afterPut(root);
            return null;
        } else {
            // 添加非根节点
            Node<K, V> node = root;
            Node<K, V> parent = root;
            int temp = 0;
            while (node != null) {
                temp = compare(key, node.key);
                if (temp == 0) {
                    V oldValue = node.value;
                    node.key = key;
                    node.value = value;
                    return oldValue;
                }
                parent = node;
                node = temp > 0 ? node.right : node.left;
            }
            Node<K, V> newNode = new Node<>(key, value, parent);
            if (temp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
            afterPut(newNode);
        }
        ++size;
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        if(node != null){
            return node.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        //调用重载方法
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if(root == null) return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node<K, V> node = queue.poll();
            if(Objects.equals(node.value,value)) return true;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K,V> visitor) {
        inorder(root,visitor);
    }

    /**
     * 中序遍历（递归）
     *
     * @param node
     * @param visitor
     */
    public void inorder(Node<K,V> node, Visitor<K,V> visitor) {
        if (node == null || visitor.stop) return;

        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.key,node.value);
        inorder(node.right, visitor);
    }

    /**
     * 比较两个元素
     *
     * @param e1
     * @param e2
     * @return temp < 0 : e1 < e2  temp > 0 e1 < e2  temp == 0  e1 = e2
     */
    private int compare(K e1, K e2) {
        // 传递了比较器
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<K>) e1).compareTo(e2);
    }

    /**
     * 删除节点
     *
     * @param node
     */
    private V remove(Node<K, V> node) {
        //删除节点
        // 没找到要删除的节点直接返回
        if (node == null) return null;
        //先处理度为二的节点,这里使用前驱去替换节点(前驱和后继节点都必须是度为0或者度为1的节点)
        V oldValue = node.value;
        if (node.hasTwoChildren()) {
            Node<K, V> prev = precessor(node);
            node.key = prev.key;
            node.value = prev.value;
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
            afterRemove(node, null);
        } else {
            //node为度为一的节点  node.parent.left(right) = node.left(right)
            Node<K, V> child = null;
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
            afterRemove(node, child);
        }
        --size;
        return oldValue;
    }

    /**
     * 非空校验
     *
     * @param key
     */
    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
    }

    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            if (compare(key, node.key) > 0) {
                //传入的元素大于当前节点，往右找
                node = node.right;
            } else if (compare(key, node.key) < 0) {
                //传入的元素小于当前节点，往左找
                node = node.left;
            } else {
                //传入的元素等于当前节点，返回该节点
                return node;
            }
        }
        return null;
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        //新添加的是根节点，根节点染黑
        if (parent == null) {
            black(node);
            return;
        }
        Node<K, V> grand = parent.parent;
        // 父节点是红色
        if (isRed(parent)) {
            Node<K, V> uncle = parent.sibling();
            // 叔父节点是红色,上溢
            if (isRed(uncle)) {
                // 父亲染黑
                black(parent);
                // 叔父染黑
                black(uncle);
                // 自己染红向上合并
                afterPut(red(grand));
                return;
            }
            // 叔父节点不红
            red(grand);
            if (parent.isLeftChild()) { //L

                if (node.isLeftChild()) {
                    //LL
                    black(parent);
                } else {
                    //LR
                    black(node);
                    rotateLeft(parent);
                }
                rotateRight(grand);
            } else {
                if (node.isLeftChild()) {
                    //RL
                    black(node);
                    rotateRight(parent);
                } else {
                    //RR
                    black(parent);
                }
                rotateLeft(grand);
            }
        }
    }

    private void afterRemove(Node<K, V> node, Node<K, V> replacement) {
        //如果被删除的节点是红色 直接返回
        if (isRed(node)) return;
        // 如果用以替代的节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        // 删除节点是黑色，用以替代的节点也是黑色（黑色叶子节点）
        Node<K, V> parent = node.parent;
        //删除节点是根节点不做处理
        if (parent == null) return;
        // 删除节点是黑色叶子节点
        // 找该节点的兄弟  （node.sibling()这时候parent和node的线断了 不能这样取）
        // parent必是度为2的节点，所以删除的节点必定是parent左右等于空的节点
        boolean isLeft = parent.left == null || node == parent.left;
        if (isLeft) {
            // 兄弟节点在右边 sibling = parent.right
            Node<K, V> sibling = parent.right;
            // 红兄弟,变成黑兄弟
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            // 黑兄弟不能借 ，左右都黑
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 父节点是红，向下合并
                if (isRed(parent)) {
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    return;
                } else {
                    // 父节点是黑，向下合并 父节点继续下溢
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    afterRemove(parent, null);
                }
            } else {
                // 黑兄弟能借
                if (isRed(sibling.left) && !isRed(sibling.right)) {
                    // RL  转换成RR统一处理
                    black(sibling.left);
                    rotateRight(red(sibling));
                    //变换兄弟
                    sibling = sibling.parent;
                }
                // 兄弟节点颜色继承父节点颜色
                color(sibling, colorOf(parent));
                // 兄弟左孩子，父节点染黑
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else {
            //兄弟节点在左边 sibling = parent.left
            Node<K, V> sibling = parent.left;
            // 红兄弟,变成黑兄弟
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            // 黑兄弟不能借 ，左右都黑
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 父节点是红，向下合并
                if (isRed(parent)) {
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    return;
                } else {
                    // 父节点是黑，向下合并 父节点继续下溢
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    afterRemove(parent, null);
                }
            } else {
                // 黑兄弟能借
                if (isRed(sibling.right) && !isRed(sibling.left)) {
                    // LR  转换成LL统一处理
                    black(sibling.right);
                    rotateLeft(red(sibling));
                    //变换兄弟
                    sibling = sibling.parent;
                }
                // 兄弟节点颜色继承父节点颜色
                color(sibling, colorOf(parent));
                // 兄弟左孩子，父节点染黑
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     * 左旋（RR）
     *
     * @param grand
     */
    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        //child 可能为空
        Node<K, V> child = parent.left;
        // g.right = p.left(child)
        grand.right = child;
        // p.left = g
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋（LL）
     *
     * @param grand
     */
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        //child 可能为空
        Node<K, V> child = parent.right;
        // g.left = p.right(child)
        grand.left = child;
        // p.right = g
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转后的统一操作
     *
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // p成为该子树的根
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //grand.parent == null
            root = parent;
        }
        // 维护 p,g,child的parent
        if (child != null) {
            child.parent = grand;
        }
        parent.parent = grand.parent;
        grand.parent = parent;
    }

    /**
     * 染色
     *
     * @param node
     * @param color
     * @return
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    /**
     * 染黑
     *
     * @param node
     * @return
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 染红
     *
     * @param node
     * @return
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 判断颜色
     *
     * @param node
     * @return
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断是否是黑
     *
     * @param node
     * @return
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断是否是红
     *
     * @param node
     * @return
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /**
     * 找node的前驱节点
     *
     * @param node
     * @return node的前驱节点
     */
    private Node<K, V> precessor(Node<K, V> node) {
        if (node == null) return node;
        // 左不为空 前驱为左子树的最右边的节点  node.left.right.right.right....
        if (node.left != null) {
            Node<K, V> left = node.left;
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
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return node;
        // 右不为空 前驱为右子树的最左边的节点  node.right.left.left.left....
        if (node.right != null) {
            Node<K, V> right = node.right;
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

    private static class Node<K, V> {
        //节点颜色，默认创建节点为红色
        boolean color = RED;
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        private Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
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

        private Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            } else if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
