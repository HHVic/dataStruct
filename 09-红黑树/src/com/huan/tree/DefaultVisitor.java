package com.huan.tree;


public class DefaultVisitor<E> extends Visitor<E> {
    @Override
    public boolean visit(E element) {
        System.out.print(element + " ");
        return false;
    }
}
