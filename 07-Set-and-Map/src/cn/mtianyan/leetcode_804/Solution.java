package cn.mtianyan.leetcode_804;

import java.util.TreeSet;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet set = new TreeSet();
        for (String word : words) {
            StringBuffer res = new StringBuffer();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']); //a充当一个初始的偏移，a-a=0 b-a=1
            }
            set.add(res.toString());
        }
        return set.size();
    }
}

