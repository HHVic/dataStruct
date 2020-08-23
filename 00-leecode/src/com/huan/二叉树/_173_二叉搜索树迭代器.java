package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * AC 23 ms, 在所有 Java 提交中击败了95.43%的用户
 */
public class _173_二叉搜索树迭代器 {
    class BSTIterator{
        //存储中序遍历序列
        List<Integer> list;
        int current = 0;
        public BSTIterator(TreeNode root) {
            list = new ArrayList<>();
            inorder(root);
        }

        public void inorder(TreeNode root){
            if(root == null) return ;
            inorder(root.left);
            list.add(root.val);
            inorder(root.right);
        }

        /** @return the next smallest number */
        public int next() {
            return list.get(current ++);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return current < list.size();
        }
    }
}
