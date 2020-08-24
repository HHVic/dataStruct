package huan.tree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 添加后的处理
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //调整平衡
                rebalance2(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //调整平衡
                rebalance2(node);
            }
        }
    }

    @Override
    protected AVLNode<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 判断是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新节点的高度
     *
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 恢复平衡
     *
     * @param grand 第一个不平衡的节点  g
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL
                rotateRight(grand);
            } else {
                //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                //RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡
     *
     * @param grand 第一个不平衡的节点  g
     */
    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                //LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                //RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                //RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    /**
     * 统一旋转
     *
     * @param r 根
     * @param a
     * @param b
     * @param g
     * @param d
     * @param e
     * @param f
     * @param g
     */
    private void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {

        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        d.parent = r.parent;
        //a,b,c
        b.left = a;
        b.right = c;
        d.left = b;

        //e,f,g
        f.left = e;
        f.right = g;
        d.right = f;

        //d
        b.parent = d;
        f.parent = d;

        if (e != null) {
            e.parent = f;
        }
        if (g != null) {
            g.parent = f;
        }

        if (a != null) {
            a.parent = b;
        }
        if (c != null) {
            c.parent = b;
        }

        //调整高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     * 左旋（RR）
     *
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        //child 可能为空
        Node<E> child = parent.left;
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
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        //child 可能为空
        Node<E> child = parent.right;
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
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
        //更新g,p的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private class AVLNode<E> extends Node<E> {
        //每个新节点创建出来必是叶子节点，高度为1
        private int height = 1;

        protected AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * @return 平衡因子
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_p(");
            String ss = parent == null ? "null" : parent.element.toString();
            sb.append(ss);
            sb.append(")");
            sb.append("_h(").append(height).append(")");
            return sb.toString();
        }
    }
}
