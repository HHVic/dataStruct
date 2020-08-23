package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 *
 *  AC 2 ms , 在所有 Java 提交中击败了47.20%的用户
 */
public class _889_根据前序和后序遍历构造二叉树 {

    //存放中序遍历[val,index]对
    static Map<Integer, Integer> map;

    public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post.length <= 0) return null;
        map = new HashMap<>();
        for (int i = 0; i < post.length; ++i) {
            map.put(post[i], i);
        }
        return buildTree(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int prel, int prer, int[] postorder, int postl, int postr) {

        if (postl > postr || prel > prer) return null;
        int val = preorder[prel];
        //根为前序遍历第一个
        TreeNode root = new TreeNode(val);
        //从中序遍历序列找到根节点
        if (prel + 1 > prer) {
            return root;
        }
        int index = map.get(preorder[prel + 1]);
        //生成左子树
        root.left = buildTree(preorder, prel + 1, prel + index - postl + 1, postorder, postl, index);
        //生成右子树
        root.right = buildTree(preorder, prel + index - postl + 2, prer, postorder, index + 1, postr - 1);
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = new int[]{2, 1};
        int[] postorder = new int[]{1, 2};
        TreeNode root = constructFromPrePost(preorder, postorder);
    }
}
