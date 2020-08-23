package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * AC 0 ms, 在所有 Java 提交中击败了100.00%的用户
 */
public class _101_对称二叉树 {

    /**
     * 递归实现
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left,root.right);
    }

    public static boolean isSymmetric(TreeNode left,TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return left.val == right.val && isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    /**
     * 非递归实现 (两个栈   左：左 中 右   右：右 中 左)(不可行)
     *
     * 使用队列
     * @param root
     */
    public static boolean isSymmetric2(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode p1 = queue.poll();
            TreeNode p2 = queue.poll();
            if(p1 == null && p2 == null) continue;
            if(p1 == null || p2 == null || p1.val != p2.val) return false;
            queue.offer(p1.left);
            queue.offer(p2.right);
            queue.offer(p1.right);
            queue.offer(p2.left);
        }
        return true;
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                1,2,2,3,4,4,3
        };
        TreeNode root = TreeNode.generateTree(arr);
        isSymmetric2(root);


    }
}
