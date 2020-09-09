package com.huan.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class _17_电话号码的字母组合 {
    public List<String> letterCombinations(String digits) {
        String[] table = generate();
        char[] chars = digits.toCharArray();
        List<char[]> charsList = new ArrayList<>();
        for (char aChar : chars) {
            charsList.add(table[Integer.parseInt(aChar + "")].toCharArray());
        }
        size = charsList.size();
        dfs(charsList,0);
        List<String> result = new ArrayList<>();
        for(List<Character> characters: res){
            StringBuilder sb = new StringBuilder();
            for(Character s : characters){
                sb.append(s);
            }
            result.add(sb.toString());
        }
        System.out.println(res);
        return result;
    }
    private String[] generate(){
        String[] table = new String[10];
        table[2] = "abc";
        table[3] = "def";
        table[4] = "ghi";
        table[5] = "jkl";
        table[6] = "mno";
        table[7] = "pqrs";
        table[8] = "tuv";
        table[9] = "wxyz";
        return table;
    }
    List<List<Character>> res = new ArrayList<>();
    List<Character> item = new ArrayList<>();
    int size;
    private void dfs(List<char[]> charsList,int start){
        if(start == size){
            res.add(new ArrayList<>(item));
            return ;
        }
        for(int i = 0;i < charsList.get(start).length;++i){
            item.add(charsList.get(start)[i]);
            dfs(charsList,start + 1);
            item.remove(start);
        }
    }

    @Test
    public void test(){
        System.out.println(letterCombinations("25"));
    }
}
