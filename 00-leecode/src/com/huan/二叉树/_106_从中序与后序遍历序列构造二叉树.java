package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 *  AC 2 ms, 在所有 Java 提交中击败了97.67%的用户
 */
public class _106_从中序与后序遍历序列构造二叉树 {

    /**
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     */
    //存放中序遍历[val,index]对
    static Map<Integer, Integer> map;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length <= 0) return null;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode buildTree(int[] inorder, int inl, int inr, int[] postorder, int postl, int postr) {

        if(inl > inr || postl > postr) return null;
        int val = postorder[postr];
        //根为后序遍历的最后一个
        TreeNode root = new TreeNode(val);
        //从中序遍历序列找到根节点
        int index = map.get(val);
        //生成左子树
        root.left = buildTree(inorder, inl, index - 1, postorder, postl, postl + index - inl - 1);
        //生成右子树
        root.right = buildTree(inorder,index + 1,inr,postorder,postl + index - inl,postr - 1);
        return root;
    }


    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = buildTree(inorder,postorder);
    }

}
