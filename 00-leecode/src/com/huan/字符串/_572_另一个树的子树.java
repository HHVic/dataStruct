package com.huan.字符串;

import com.huan.二叉树.dataType.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/   递归常规做法
 */
public class _572_另一个树的子树 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        //序列化
        return postSerialize(s).contains(postSerialize(t));
    }

    private String postSerialize(TreeNode root){
        return postSerialize(root,new StringBuilder()).toString();
    }

    private StringBuilder postSerialize(TreeNode root,StringBuilder sb){
        if(root.left == null){
            sb.append("#!");
        }else{
            postSerialize(root.left,sb);
        }
        if(root.right == null){
            sb.append("#!");
        }else{
            postSerialize(root.right,sb);
        }
        return sb.append(root.val).append("!");
    }
}
