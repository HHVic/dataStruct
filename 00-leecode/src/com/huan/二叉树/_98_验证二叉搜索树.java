package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class _98_验证二叉搜索树 {

    /**
     *0 ms, 在所有 Java 提交中击败了100.00%的用户
     * @param root
     * @return
     */
    public static boolean isValidBSTRecurce(TreeNode root) {
        return isValidBSTRecurce(root, null, null);
    }

    public static boolean isValidBSTRecurce(TreeNode root, Integer upper, Integer lower) {
        if(root == null) return true;
        int val = root.val;

        if(upper != null && val >= upper) return false;
        if(lower != null && val <= lower) return false;

        if(!isValidBSTRecurce(root.left,val,lower) || !isValidBSTRecurce(root.right,upper,val)) return false;
        return true;
    }

    /**
     * 2 ms, 在所有 Java 提交中击败了32.14%的用户
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        double prev = -Double.MAX_VALUE;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return true;
            } else {
                node = stack.pop();
                if (node.val <= prev) return false;
                prev = node.val;
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                5, 1, 4, null, null, 3, 6
        };
        TreeNode root = TreeNode.generateTree(arr);
        isValidBST(root);
    }
}
