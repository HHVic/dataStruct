package com.huan;


public class DefaultVisitor<E> extends Visitor<E> {
    @Override
    boolean visit(E element) {
        System.out.print(element + " ");
        return false;
    }
}
