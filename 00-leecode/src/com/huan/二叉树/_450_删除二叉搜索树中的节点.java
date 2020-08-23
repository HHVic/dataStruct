package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 *      AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _450_删除二叉搜索树中的节点 {

    public static TreeNode deleteNode(TreeNode root, int key) {
        //找到要删除的节点
        if (root == null) return root;
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            if (key == node.val) {
                break;
            } else if (key < node.val) {
                parent = node;
                node = node.left;
            } else {
                parent = node;
                node = node.right;
            }
        }
        if(node == null) return root;
        TreeNode replace = null;
        if (node.left != null && node.right != null) {
            parent = node;
            replace = node.left;
            while (replace.right != null) {
                parent = replace;
                replace = replace.right;
            }
            node.val = replace.val;
            node = replace;
        }
        if(node.left == null && node.right == null){
            //要删除的是叶子
            if(parent == null){
                root = null;
            }else if(node == parent.left){
                parent.left = null;
            }else{
                parent.right = null;
            }
        } else{
            TreeNode child = null;
            if (node.left != null) {
                child = node.left;
            } else {
                child = node.right;
            }
            if (parent != null) {
                if(parent.left == node){
                    parent.left = child;
                }else {
                    parent.right = child;
                }
            }else{
                root = child;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                5
        };
        TreeNode root = TreeNode.generateTree(arr);
        root = deleteNode(root,5);

    }
}
