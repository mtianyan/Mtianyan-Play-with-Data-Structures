package cn.mtianyan.leetcode_349;

import java.util.ArrayList;
import java.util.TreeSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        // 对于nums1进行去重存入set
        for (int num : nums1) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>();
        // 对于nums2进行遍历
        for (int num : nums2) {
            // 如果是set中已经有的元素，那么就加入结果ArrayList中
            if (set.contains(num)) {
                list.add(num);
                // 将set中该元素去除，那么num2中重复的不会再被匹配到，因为set是删一个少一个。
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
