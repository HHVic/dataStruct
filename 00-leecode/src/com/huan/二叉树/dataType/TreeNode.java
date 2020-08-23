package com.huan.二叉树.dataType;

/**
 * 二叉树
 */
public class TreeNode {
    //树的属性
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    //通过对象数组生成一棵树
    public static TreeNode generateTree(Integer[] obj) {
        if(obj == null){
            return null;
        }
        return generateTree(obj,0);
    }

    public static TreeNode generateTree(Integer[] obj, int index) {
        TreeNode root = null;
        if (index >= obj.length) {
            return root;
        }
        Integer value = obj[index];
        if (value == null) {
            return null;
        }
        root = new TreeNode(value);
        root.left = generateTree(obj, 2 * index + 1);
        root.right = generateTree(obj, 2 * index + 2);
        return root;
    }
    //判断两棵树是否相同
    public static  boolean isSimilar(TreeNode t1,TreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }
        return t1.val == t2.val && isSimilar(t1.left,t2.left) && isSimilar(t1.right,t2.right);
    }
}
