package cn.mtianyan.leetcode_211;

/// Leetcode 211. Add and Search Word - Data structure design
/// https://leetcode.com/problems/add-and-search-word-data-structure-design/description/

import java.util.TreeMap;

public class WordDictionary {

    /**
     * 节点类：isWord，Map<c,node>
     */
    private class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next; // 中文单词界限模糊

        // 传入isWord
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        // 无参构造函数
        public Node() {
            this(false);
        }
    }

    private Node root;  // 根节点

    /**
     * 默认构造函数，初始化节点
     */
    public WordDictionary() {
        root = new Node();
    }

    /**
     * 添加一个单词到数据结果中
     */
    public void addWord(String word) {

        Node cur = root;
        // 不存在创建
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        // 最差情况全是点
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        // 递归到底，整个word已经考虑完了
        if (index == word.length())
            return node.isWord;

        char c = word.charAt(index);

        // 具体字母
        if (c != '.') {
            if (node.next.get(c) == null)
                return false; // 字符串匹配失败
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet())
                if (match(node.next.get(nextChar), word, index + 1))
                    return true;
            return false;
        }
    }
}
