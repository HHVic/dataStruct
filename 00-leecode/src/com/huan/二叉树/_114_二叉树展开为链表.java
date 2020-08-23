package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 *  AC 1 ms在所有 Java 提交中击败了43.77%的用户
 */
public class _114_二叉树展开为链表 {

    static List<TreeNode> list = null;
    public static void flatten(TreeNode root) {
        if(root == null) return ;
        list = new ArrayList<>();
        flatten1(root);
        TreeNode tail = root;
        for(int i = 1;i < list.size();++i){
            tail.left = null;
            tail.right = list.get(i);
            tail = tail.right;
        }
    }

    public static void flatten1(TreeNode root){
        if(root == null) return ;
        list.add(root);
        flatten1(root.left);
        flatten1(root.right);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                1,2,5,3,4,null,6
        };
        TreeNode root = TreeNode.generateTree(arr);

        flatten(root);
    }
}
