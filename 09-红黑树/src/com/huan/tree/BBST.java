package com.huan.tree;

/**
 * 在BST下增加旋转操作
 * @param <E>
 */
public class BBST<E> extends BST<E> {
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
    protected void rotate(Node<E> r,
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
    }

    /**
     * 左旋（RR）
     *
     * @param grand
     */
    protected void rotateLeft(Node<E> grand) {
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
    protected void rotateRight(Node<E> grand) {
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
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
}
