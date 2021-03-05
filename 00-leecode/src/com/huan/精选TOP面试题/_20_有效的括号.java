package com.huan.精选TOP面试题;

import org.junit.Test;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.Stack;

/**
 * @author:HuanK
 * @create:2021-03-01 11:40
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_有效的括号 {
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        if("".equals(s)) return true;
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < arr.length;++i) {
            if (isLeft(arr[i])) {
                stack.push(arr[i]);
                continue;
            }
            if (stack.isEmpty() || !isMatch(stack.pop(), arr[i]))  return false;
        }
        return stack.isEmpty();
    }

    private boolean isMatch(char left, char right) {

        return left == '(' && right == ')' || left == '[' && right == ']' || left == '{' && right == '}';
    }

    private boolean isLeft(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    @Test
    public void test(){
        System.out.println(isValid("((()))[]{}[{]}"));
    }

}
