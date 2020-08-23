package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 * AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _701_二叉搜索树中的插入操作 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root == null) return newNode;
        TreeNode node = root;
        TreeNode parent = null;
        while(node != null){
            if(val < node.val){
                parent = node;
                node = node.left;
            }else{
                parent = node;
                node = node.right;
            }
        }
        if(val < parent.val){
            parent.left = newNode;
        }else {
            parent.right = newNode;
        }
        return root;
    }
}
