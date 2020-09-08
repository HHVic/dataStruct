package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 */
public class _654_最大二叉树 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums,0,nums.length - 1);
    }

    /**
     * 在[left,right]区间构建二叉树
     * @param value
     * @param left
     * @param right
     */
    private TreeNode buildTree(int[] nums, int left, int right){
        if(left > right) return null;
        int maxInx = left;
        for(int i = left + 1;i <= right;++i){
            if(nums[maxInx] < nums[i]) maxInx = i;
        }
        TreeNode root = new TreeNode(nums[maxInx]);
        root.left = buildTree(nums,left,maxInx - 1);
        root.right = buildTree(nums,maxInx + 1,right);
        return root;
    }
}
