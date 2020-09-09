package com.huan.二叉树;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/   递归常规做法 (效率太差) 字符串匹配
 */
public class _572_另一个树的子树 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        dfs(s,t);
        return stop;
    }
    boolean stop = false;
    private void dfs(TreeNode s,TreeNode t){
        if(s == null || stop) return ;
        if(s.val == t.val && isSimilar(s,t)){
            stop = true;
            return ;
        }
        dfs(s.left,t);
        dfs(s.right,t);
    }



    private boolean isSimilar(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        return root1.val == root2.val && isSimilar(root1.left,root2.left) && isSimilar(root1.right,root2.right);

    }
}
