package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 *
 * AC  13MS  5.81%
 */

public class _662_二叉树最大宽度 {

    public static int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.offer(root);
        int size = 1;
        int max = 1;

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            --size;
            int temp = node.val;
            if(node.left != null){
                node.left.val = 2 * temp;
                queue.offer(node.left);
            }
            if(node.right != null){
                node.right.val = 2 * temp + 1;
                queue.offer(node.right);
            }
            if(queue.isEmpty()){
                break;
            }

            if(size == 0){
                max = Math.max(queue.getLast().val - queue.getFirst().val + 1,max);
                size = queue.size();
                System.out.println(max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2
        };
        TreeNode root = TreeNode.generateTree(arr);

        int width = widthOfBinaryTree(root);
        System.out.println(width);
    }
}
