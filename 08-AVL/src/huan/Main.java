package huan;

import huan.printer.BinaryTrees;
import huan.tree.AVLTree;
import huan.tree.BST;
import huan.tree.BinaryTree;
import huan.tree.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{
                81, 55, 92, 21, 88, 78, 2, 3, 85, 36, 75, 33, 91, 9, 77, 44, 32, 96, 48, 80
        };
//
//
        AVLTree<Integer> tree = new AVLTree<>();
        for(Integer x : nums){
            tree.add(x);
//            System.out.println("【" + x + "】");
//            BinaryTrees.println(tree);
//            System.out.println("=========================");
        }

//        for(Integer x : nums){
//            tree.remove(x);
//            System.out.println("【" + x + "】");
//            BinaryTrees.println(tree);
//            System.out.println("=========================");
//        }
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
        }, BinaryTree.OrderType.INORDER);
        System.out.println();
        //System.out.println(tree.height());
        System.out.println();
        List<Integer> list = new ArrayList<>();
//        System.out.println(tree.precessor(7));
//        System.out.println(tree.successor(6));
        BinaryTrees.println(tree);
    }
}
