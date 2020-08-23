package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 * AC 9 ms, 在所有 Java 提交中击败了5.92%的用户
 */
public class _938_二叉搜索树的范围和 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int sum = 0;
        boolean start = false;
        while(true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else if(stack.isEmpty()){
                return sum;
            }else{
                node = stack.pop();
                if(node.val == L){
                    start = true;
                }
                if(start){
                    sum += node.val;
                }
                if(node.val == R){
                    return sum;
                }
                node = node.right;
            }
        }
    }

    boolean start = false;
    int sum = 0;
    boolean stop = false;
    public int rangeSumBSTRecurce(TreeNode root, int L, int R) {
        inorder(root,L,R);
        return sum;
    }

    public void inorder(TreeNode root,int L,int R){
        if(root == null || stop) return;
        inorder(root.left,L,R);
        if(stop) return ;
        if(root.val == L){
            start = true;
        }
        if(start){
            sum += root.val;
        }
        if(root.val == R){
            stop = true;
            return ;
        }
        inorder(root.right,L,R);
    }
}
