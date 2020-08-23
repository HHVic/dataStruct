package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 *        AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _700_二叉搜索树中的搜索 {

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return root;
        TreeNode node = root;
        while(node != null){
            if(val == node.val) return node;
            else if(val < node.val){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return null;
    }
}
