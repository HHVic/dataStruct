package com.huan.map;

import com.huan.printer.BinaryTreeInfo;
import com.huan.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap<K, V> implements Map<K, V> {

    private static final boolean BLACK = true;
    private static final boolean RED = false;
    private static final int DEFAULT_CAPACITY = (1 << 4);
    private int size;
    private Node<K, V>[] table;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
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
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        // 添加根节点
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            ++size;
            afterPut(root);
            return null;
        }
        // 添加非根节点
        Node<K, V> node = root;
        Node<K, V> parent = root;
        int h1 = key == null ? 0 : key.hashCode();
        int temp = 0;
        boolean searched = false;
        Node<K, V> result = null;
        do {
            int h2 = node.hash;
            K k2 = node.key;
            if (h1 > h2) {
                temp = 1;
            } else if (h1 < h2) {
                temp = -1;
            } else if (Objects.equals(key, k2)) {
                temp = 0;
            } else if (key != null && k2 != null && key.getClass() == k2.getClass()
                    && key instanceof Comparable
                    && (temp = ((Comparable) key).compareTo(k2)) != 0) {
                //通过compareTo的结果判断往左还是往右，两个key相同的 唯一条件是equals
            } else if (searched) {
                //保证只要扫一遍
                temp = System.identityHashCode(key) - System.identityHashCode(k2);
            } else {
                if ((node.right != null && (result = node(node.right, key)) != null)
                        || (node.left != null && (result = node(node.left, key)) != null)) {
                    //存在这个key
                    temp = 0;
                    node = result;
                } else {
                    // 不存在这个key
                    temp = System.identityHashCode(key) - System.identityHashCode(k2);
                    searched = true;
                }
            }
            if (temp == 0) {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
            parent = node;
            node = temp > 0 ? node.right : node.left;
        } while (node != null);

        Node<K, V> newNode = new Node<>(key, value, parent);
        if (temp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        afterPut(newNode);
        ++size;
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        Node<K, V> node = node(key);
        return remove(node);
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) == null ? false : true;
    }

    @Override
    public boolean containsValue(V value) {
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(node.value, value)) return true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public void print() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.print(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("=======================");
        }
        System.out.println("---------------------------");

    }

    /**
     * 通过key计算index索引
     *
     * @param key
     * @return
     */
    private int index(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length - 1);
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
            int index = index(grand.key);
            table[index] = parent;
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
     * @param k1
     * @param k2
     * @param h1 k1 的hash
     * @param h2 k2 的hash
     * @return
     */
    private int compare(K k1, K k2, int h1, int h2) {
        // 首先通过比较hash
        int result = h1 - h2;
        // hash值不相同
        if (result != 0) return result;
        // 通过equals比较
        if (Objects.equals(k1, k2)) return 0;
        // k1 k2 都不空
        if (k1 != null && k2 != null) {
            // hash值相同 (比较类名)
            String clazz1 = k1.getClass().getName();
            String clazz2 = k2.getClass().getName();
            // 类型不相同
            if (!Objects.equals(clazz1, clazz2)) return clazz1.compareTo(clazz2);
            // k1 k2同一类型，并且实现了Comparable接口
            if (k1 instanceof Comparable) return ((Comparable) k1).compareTo(k1);
        }
        //k1 k2 都空 返回0
        if (k1 == null && k2 == null) return 0;
        // k1 k2 有一个空，默认空放左边
        if (k1 == null) {
            return -1;
        } else if (k2 == null) {
            return 1;
        }
        // k1 k2 是相同类型 并且都不空
        // 通过属性来比较
        return System.identityHashCode(k1) - System.identityHashCode(k2);
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
        int index = index(node.key);
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
                table[index] = null;
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
                table[index] = child;
            }
            afterRemove(node, child);
        }
        --size;
        return oldValue;
    }

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        if (root == null) return null;
        return node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K key) {
        int h1 = key == null ? 0 : key.hashCode();
        Node<K, V> result = null;
        int temp = 0;
        while (node != null) {
            // 判断hash值是否相等
            int h2 = node.hash;
            K k2 = node.key;
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else {
                // hash值相同，判断是否equals
                if (Objects.equals(key, k2)) return node;
                // hash值相同又不equals
                if (key != null && k2 != null && key.getClass() == k2.getClass()
                        && key instanceof Comparable
                        && (temp = ((Comparable) key).compareTo(k2)) != 0) {
                    node = temp > 0 ? node.right : node.left;
                } else if (node.right != null && (result = node(node.right, key)) != null) {//hash 相同 不equals 不可比较
                    // 向右扫描
                    return result;
                } else {
                    node = node.left;
                }
//                } else if (node.left != null && (result = node(node.left, key)) != null) {//hash 相同 不equals 不可比较
//                    // 向左扫描
//                    return result;
//            } else{
//                return null;
//            }
        }
    }
        return null;
}

private static class Node<K, V> {

    int hash;
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
        this.hash = (key == null) ? 0 : key.hashCode();
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(key).append("->").append(value);
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
