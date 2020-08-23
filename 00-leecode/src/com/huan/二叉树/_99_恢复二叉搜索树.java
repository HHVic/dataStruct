package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * AC 4 ms, 在所有 Java 提交中击败了38.54%的用户
 */
public class _99_恢复二叉搜索树 {
    //空间O(n)
    public static void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;
        TreeNode change = root;
        boolean desc = false;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                if(desc && stack.isEmpty()){
                    //执行交换
                    int temp = prev.val;
                    prev.val = change.val;
                    change.val = temp;
                    return ;
                }else if(stack.isEmpty()){
                    return ;
                }
                node = stack.pop();
                if(desc && change.val < node.val){
                    //执行交换
                    int temp = prev.val;
                    prev.val = change.val;
                    change.val = temp;
                    return ;
                }
                if(!desc && prev != null && prev.val >= node.val){
                    //记录prev标记
                    change = prev;
                    desc = true;
                }
                prev = node;
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                146,71,-13,55,null,231,399,321,null,null,null,null,null,-33
        };
        TreeNode root = TreeNode.generateTree(arr);
        recoverTree(root);
    }
}
