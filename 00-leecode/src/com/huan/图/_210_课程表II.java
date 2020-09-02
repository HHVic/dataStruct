package com.huan.图;

import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/course-schedule-ii/
 * AC 27 ms,在所有 Java 提交中击败了的用户
 */
public class _210_课程表II {
    /*
    示例 1:
    输入: 2, [[1,0]]
    输出: [0,1]
    解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

    示例 2:
    输入: 4, [[1,0],[2,0],[3,1],[3,2]]
    输出: [0,1,2,3] or [0,2,1,3]
    解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
         因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     */

    //定义map存储所有节点的入度
    int[] ins;
    //存放顶点
    Set<Integer> vertices = new HashSet<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ins = new int[numCourses];
        initIns(prerequisites);
        int[] res = new int[numCourses];
        if(prerequisites.length == 0){
            for(int i = 0;i < numCourses;++i){
                res[i] = i;
            }
            return res;
        }
        Queue<Integer> queue = new LinkedList<>();
        //入度为0入队
        for(int[] prerequisite : prerequisites){
            if(ins[prerequisite[1]] == 0 && !queue.contains(prerequisite[1])){
                queue.offer(prerequisite[1]);
            }
        }
        if(queue.isEmpty()){
            return new int[]{};
        }

        int t = 0;
        while(!queue.isEmpty() && vertices.size() < numCourses){
            int temp = queue.poll();
            //if(vertices.contains(temp)) continue;
            res[t++] = temp;
            vertices.add(temp);
            //遍历被temp[1]指向的
            for(int[] prerequisite : prerequisites){
                if(prerequisite[1] == temp && --ins[prerequisite[0]] == 0){
                    queue.offer(prerequisite[0]);
                    //vertices.add(prerequisite[1]);
                }
            }
        }

        if(!queue.isEmpty()){
            return new int[]{};
        }
        for(int i = 0;i < numCourses;++i){
            if(!vertices.contains(i) && ins[i] == 0){
                vertices.add(i);
                res[t++] = i;
            }
        }
        if(vertices.size() < numCourses){
            return new int[]{};
        }
        return res;
    }

    /**
     * 初始化所有结点的入度
     * @param prerequisites
     */
    public void initIns(int[][] prerequisites){
        for(int[] prerequisite : prerequisites){
            ++ins[prerequisite[0]];
        }
    }

    @Test
    public void test(){
        int[][] prerequisites = new int[][]{
                {1,0}
        };
        System.out.println(Arrays.toString(findOrder(2, prerequisites)));
    }
}
