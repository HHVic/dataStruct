package com.huan.二叉树;

import com.huan.二叉树.dataType.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 *
 * AC 3 ms, 在所有 Java 提交中击败了19.37%的用户
 */
public class _559_N叉树的最大深度 {

    public static int maxDepth(Node root) {
        if(root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int height = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            --size;
            for(Node p : node.children){
                queue.offer(p);
            }
            if(size == 0){
                ++height;
                size = queue.size();
            }
        }
        return height;
    }

    public static void main(String[] args) {

    }
}
