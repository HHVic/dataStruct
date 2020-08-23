package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _230_二叉搜索树中第K小的元素 {
    //中序遍历第k个
    int count = 0;
    boolean stop = false;
    int result;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return result;
    }
    public void inorder(TreeNode root,int k){
        if(root == null || stop) return ;
        inorder(root.left,k);
        if(stop) return ;
        ++count;
        if(k == count){
            result = root.val;
            stop = true;
            return ;
        }
        inorder(root.right,k);
    }
}
