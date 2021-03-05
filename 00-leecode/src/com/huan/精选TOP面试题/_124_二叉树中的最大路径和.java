package com.huan.精选TOP面试题;

import com.huan.二叉树.dataType.TreeNode;

/**
 * @author:HuanK
 * @create:2021-02-28 16:41
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class _124_二叉树中的最大路径和 {
    //maxPathMax(root)表示 经过root节点的最大路径和
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxRoot(root);
        return res;
    }

    private int maxRoot(TreeNode root){
        if(root == null) return 0;
        int leftMax = Math.max(maxRoot(root.left),0);
        int rightMax = Math.max(maxRoot(root.right),0);
        int sumMax = leftMax + root.val + rightMax;
        res = Math.max(res,sumMax);
        return root.val + Math.max(leftMax,rightMax);
    }
}
