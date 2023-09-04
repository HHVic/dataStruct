package com.huan.tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    public RBTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public RBTree() {
        this(null);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //新添加的是根节点，根节点染黑
        if(parent == null){
            black(node);
            return ;
        }
        Node<E> grand = parent.parent;
        // 父节点是红色
        if(isRed(parent)){
            Node<E> uncle = parent.sibling();
            // 叔父节点是红色,上溢
            if(isRed(uncle)){
                // 父亲染黑
                black(parent);
                // 叔父染黑
                black(uncle);
                // 自己染红向上合并
                afterAdd(red(grand));
                return ;
            }
            // 叔父节点不红
            red(grand);
            if(parent.isLeftChild()){ //L

                if(node.isLeftChild()){
                    //LL
                    black(parent);
                }else{
                    //LR
                    black(node);
                    rotateLeft(parent);
                }
                rotateRight(grand);
            }else{
                if(node.isLeftChild()){
                    //RL
                    black(node);
                    rotateRight(parent);
                }else{
                    //RR
                    black(parent);
                }
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        //如果被删除的节点是红色 直接返回
        if(isRed(node)) return ;
        // 如果用以替代的节点是红色
        if(isRed(replacement)){
            black(replacement);
            return ;
        }
        // 删除节点是黑色，用以替代的节点也是黑色（黑色叶子节点）
        Node<E> parent = node.parent;
        //删除节点是根节点不做处理
        if(parent == null) return ;
        // 删除节点是黑色叶子节点
        // 找该节点的兄弟  （node.sibling()这时候parent和node的线断了 不能这样取）
        // parent必是度为2的节点，所以删除的节点必定是parent左右等于空的节点
        boolean isLeft = parent.left == null || node == parent.left;
        if(isLeft){
            // 兄弟节点在右边 sibling = parent.right
            Node<E> sibling = parent.right;
            // 红兄弟,变成黑兄弟
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            // 黑兄弟不能借 ，左右都黑
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                // 父节点是红，向下合并
                if(isRed(parent)){
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    return ;
                }else{
                    // 父节点是黑，向下合并 父节点继续下溢
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    afterRemove(parent,null);
                }
            }else {
                // 黑兄弟能借
                if(isRed(sibling.left) && !isRed(sibling.right)){
                    // RL  转换成RR统一处理
                    black(sibling.left);
                    rotateRight(red(sibling));
                    //变换兄弟
                    sibling = sibling.parent;
                }
                // 兄弟节点颜色继承父节点颜色
                color(sibling,colorOf(parent));
                // 兄弟左孩子，父节点染黑
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        }else{
            //兄弟节点在左边 sibling = parent.left
            Node<E> sibling = parent.left;
            // 红兄弟,变成黑兄弟
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            // 黑兄弟不能借 ，左右都黑
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                // 父节点是红，向下合并
                if(isRed(parent)){
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    return ;
                }else{
                    // 父节点是黑，向下合并 父节点继续下溢
                    // 父节点染黑，兄弟染红
                    black(parent);
                    red(sibling);
                    afterRemove(parent,null);
                }
            }else {
                // 黑兄弟能借
                if(isRed(sibling.right) && !isRed(sibling.left)){
                    // LR  转换成LL统一处理
                    black(sibling.right);
                    rotateLeft(red(sibling));
                    //变换兄弟
                    sibling = sibling.parent;
                }
                // 兄弟节点颜色继承父节点颜色
                color(sibling,colorOf(parent));
                // 兄弟左孩子，父节点染黑
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
    }

    /**
     * 染色
     * @param node
     * @param color
     * @return
     */
    private Node<E> color(Node<E> node,boolean color){
        if(node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    /**
     * 染黑
     * @param node
     * @return
     */
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    /**
     * 染红
     * @param node
     * @return
     */
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    /**
     * 判断颜色
     * @param node
     * @return
     */
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    /**
     * 判断是否是黑
     * @param node
     * @return
     */
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    /**
     * 判断是否是红
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E>{
        //节点颜色，默认创建节点为红色
        boolean color = RED;

        protected RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if(color == RED){
                sb.append("red_");
            }
            sb.append(element);
//            sb.append("_p(");
//            String ss = parent == null ? "null" : parent.element.toString();
//            sb.append(ss);
//            sb.append(")");
            return sb.toString();
        }
    }
}
