package com.huan.字典树;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class _208_实现Trie {
    /**
     * Initialize your data structure here.
     */
    private Node root;

    public _208_实现Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean emptyChildren = node.children == null;
            Node childNode = emptyChildren ? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node();
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            node = childNode;
        }
        if(!node.word){
            node.word = true;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node node = node(word);
        return node != null && node.word;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    private Node node(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = word.charAt(i);
            node = node.children.get(c);
        }
        return node;
    }

    private static class Node {
        boolean word;
        Map<Character, Node> children;
    }

    public static void main(String[] args) {
        _208_实现Trie trie = new _208_实现Trie();
        trie.insert("aaaa");
        trie.insert("bbad");
        trie.insert("abaa");
        trie.insert("cc");
        trie.insert("ao");
        trie.insert("zmg");
        System.out.println(trie.search("aaaa"));
        System.out.println(trie.search("aaa"));
        System.out.println(trie.search("ao"));
        System.out.println(trie.search("ccc"));
        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("v"));
        System.out.println(trie.startsWith("b"));
    }
}
