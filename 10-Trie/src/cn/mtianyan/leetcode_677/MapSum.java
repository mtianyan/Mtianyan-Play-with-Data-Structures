package cn.mtianyan.leetcode_677;

import java.util.TreeMap;

public class MapSum {

    private class Node{

        public int value; // 单词权重值 isWord可以省略，0不是单词。
        public TreeMap<Character, Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {

        root = new Node();
    }

    public void insert(String key, int val) {

        Node cur = root;
        // 单词添加Trie,并添加value值。
        for(int i = 0 ; i < key.length() ; i ++){
            char c = key.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        // 先找到前缀最后一个字母所在位置。
        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node){
        // cur 为根节点的单词，所有节点value值相加。
        int res = node.value;
        for(char c: node.next.keySet())
            res += sum(node.next.get(c));
        return res;
    }
}

