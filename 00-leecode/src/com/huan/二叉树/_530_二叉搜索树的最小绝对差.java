package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/submissions/
 *
 * AC 2 ms , 在所有 Java 提交中击败了31.57%的用户
 */
public class _530_二叉搜索树的最小绝对差 {
    public static int getMinimumDifference(TreeNode root) {
        int prev = -1;
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return min;
            } else {
                node = stack.pop();
                if(prev < 0){
                    prev = node.val;
                    node = node.right;
                    continue;
                }
                min = Math.min(node.val - prev,min);
                prev = node.val;
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                15,3,19,1,4,null,26
        };
        TreeNode root = TreeNode.generateTree(arr);
        int min = getMinimumDifference(root);
        System.out.println(min);
    }
}
