package com.huan;

import com.huan.printer.BinaryTrees;
import com.huan.tree.BST;
import com.huan.tree.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{
              2
        };
//
//
        BST<Integer> tree = new BST<>();
        for(Integer x : nums){
            tree.add(x);
        }
//        BinaryTrees.println(bst1);
//        bst1.order(new Visitor<Integer>() {
//            @Override
//            boolean visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//                if(element == 4) return true;
//                return false;
//            }
//        },BinarySearchTree.OrderType.LEVEL_ORDER);
//        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//        for (int i = 0; i < 10; ++i) {
//            tree.add((int) (Math.random() * 100));
//        }
        BinaryTrees.println(tree);
        tree.order(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("_" + element + "_ ");
                return false;
            }
        }, BST.OrderType.INORDER);
        System.out.println();
        //System.out.println(tree.height());

        List<Integer> list = new ArrayList<>();
//        System.out.println(tree.precessor(7));
//        System.out.println(tree.successor(6));
        tree.remove(2);
        BinaryTrees.println(tree);
    }
}
