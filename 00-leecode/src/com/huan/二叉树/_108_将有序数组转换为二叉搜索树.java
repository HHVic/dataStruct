package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _108_将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length <= 0) return null;
        return buildTree(nums,0,nums.length - 1);
    }

    public TreeNode buildTree(int[] nums,int l,int r){
        if(l > r) return null;
        int mid = l + ((r - l) >> 1);
        int val = nums[mid];
        TreeNode node = new TreeNode(val);
        node.left = buildTree(nums,l,mid - 1);
        node.right = buildTree(nums,mid + 1,r);
        return node;
    }
}
