package com.huan.回溯;

import com.huan.二叉树.dataType.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
 */
public class _637_二叉树的层平均值 {
    //解法1 层序遍历
//    public List<Double> averageOfLevels(TreeNode root) {
//        List<Double> result = new ArrayList<>();
//        double sum = 0.0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        int size = 1;
//        int count = 0;
//        while(!queue.isEmpty()){
//            TreeNode node = queue.poll();
//            --size;
//            ++count;
//            sum += node.val;
//            if(node.left != null){
//                queue.offer(node.left);
//            }
//            if(node.right != null){
//                queue.offer(node.right);
//            }
//            if(size == 0){
//                result.add(sum / count);
//                count = 0;
//                sum = 0;
//                size = queue.size();
//            }
//        }
//        return result;
//    }

    //解法2 回溯
    List<Double> sums = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        dfs(root,0);
        for(int i = 0;i < sums.size();++i){
            result.add(sums.get(i) / counts.get(i));
        }
        return result;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        //说明该层有值
        if(level < sums.size()){
            sums.set(level,sums.get(level) + node.val);
            counts.set(level,counts.get(level) + 1);
        }else{
            sums.add(level, (double) node.val);
            counts.add(level,1);
        }
        dfs(node.left,level + 1);
        dfs(node.right,level + 1);

    }


}
