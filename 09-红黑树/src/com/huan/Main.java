package com.huan;

import com.huan.printer.BinaryTrees;
import com.huan.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static int NUM = 100000000;
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{
                19, 13, 63, 83, 54, 75, 66, 78, 35, 18, 26, 46, 67, 12, 77, 2, 50, 76
        };
//
//
        RBTree<Integer> tree = new RBTree<>();
        for(Integer x : nums){
            tree.add(x);
//            System.out.println("【" + x + "】");
//            BinaryTrees.println(tree);
//            System.out.println("=========================");
        }

        BinaryTrees.println(tree);
        System.out.println("=========================");
        for(Integer x : nums){
            tree.remove(x);
            System.out.println("【" + x + "】");
            BinaryTrees.println(tree);
            System.out.println("=========================");
        }


        BinaryTrees.println(tree);
//        tree.order(new Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//                return false;
//            }
//        }, BinaryTree.OrderType.INORDER);


//        List<Integer> list = new ArrayList<>();
//        for(int i = 0; i < NUM; ++i){
//            list.add((int) (Math.random() * NUM));
//        }
//        System.out.println("数据准备完毕");
//        test2(list);
//        test1(list);

    }

    public static void test1(List<Integer> list){
        BST<Integer> tree = new BST<>();
        long start = System.currentTimeMillis();
        //插入
        for(int i = 0;i < NUM;++i){
            tree.add(list.get(i));
        }
        //查找
        for(int i = 0;i < NUM;++i){
            tree.contains(list.get(i));
        }
        //删除
        for(int i = 0;i < NUM;++i){
            tree.remove(list.get(0));
        }
        long end = System.currentTimeMillis();
        System.out.println("BST执行用时：" + ((end - start) / 1000.0));
    }

    public static void test2(List<Integer> list){
        AVLTree<Integer> tree = new AVLTree<>();
        long start = System.currentTimeMillis();
        //插入
        for(int i = 0;i < NUM;++i){
            tree.add(list.get(i));
        }
        //查找
        for(int i = 0;i < NUM;++i){
            tree.contains(list.get(i));
        }
        //删除
        for(int i = 0;i < NUM;++i){
            tree.remove(list.get(0));
        }
        long end = System.currentTimeMillis();
        System.out.println("AVL执行用时：" + ((end - start) / 1000.0));
    }
}
