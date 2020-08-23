package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * AC 3 ms, 在所有 Java 提交中击败了76.20%的用户
 */
public class _105_从前序与中序遍历序列构造二叉树 {
    //存放中序遍历[val,index]对
    static Map<Integer, Integer> map;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || inorder.length <= 0) return null;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int prel, int prer, int inl, int inr) {

        if (inl > inr || prel > prer) return null;
        int val = preorder[prel];
        //根为前序遍历第一个
        TreeNode root = new TreeNode(val);
        //从中序遍历序列找到根节点
        int index = map.get(val);
        //生成左子树
        root.left = buildTree(preorder, prel + 1, prel + index - inl, inl, index - 1);
        //生成右子树
        root.right = buildTree(preorder, prel + index - inl + 1, prer, index + 1, inr);
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
    }
}
