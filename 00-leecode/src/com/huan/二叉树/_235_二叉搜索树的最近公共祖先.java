package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * AC 6 ms, 在所有 Java 提交中击败了 99.77%的用户
 */
public class _235_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val = root.val;
        //若pq在不同子树 公共祖先为当前节点
        if(val > p.val && val < q.val || val > q.val && val < p.val) return root;
        //在相同子树看根是否等与p.val q.val
        if(val == p.val) return p;
        if(val == q.val) return q;
        //在相同子树
        if(val > p.val){
            //左子树
            return lowestCommonAncestor(root.left,p,q);
        }else {
            return lowestCommonAncestor(root.right,p,q);
        }
    }
}
