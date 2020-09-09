package com.huan.字符串;

import com.huan.二叉树.dataType.TreeNode;

import java.util.Iterator;
import java.util.Objects;

/**
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
public class _297_二叉树的序列化与反序列化 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#!";
        return preSerialize(root,new StringBuilder()).toString();
    }

    /**
     * 先序遍历序列化二叉树
     * @param root
     * @param sb
     * @return
     */
    private StringBuilder preSerialize(TreeNode root,StringBuilder sb){
        sb.append(root.val).append("!");
        if(root.left == null){
            sb.append("#!");
        }else{
            preSerialize(root.left,sb);
        }
        if(root.right == null){
            sb.append("#!");
        }else{
            preSerialize(root.right,sb);
        }
        return sb;
    }


    public TreeNode deserialize(String data) {
        String[] datas = data.split("!");
        return deserialize(datas);
    }
    int index = -1;
    private TreeNode deserialize(String[] datas){
        if(Objects.equals(datas[++index],"#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(datas[index]));
        root.left = deserialize(datas);
        root.right = deserialize(datas);
        return root;
    }
}
