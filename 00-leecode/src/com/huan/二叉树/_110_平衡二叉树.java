package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * AC 1 ms, 在所有 Java 提交中击败了99.86%的用户
 */
public class _110_平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode node){
        if(node == null) return 0;
        return Math.max(height(node.left),height(node.right)) + 1;
    }
}
