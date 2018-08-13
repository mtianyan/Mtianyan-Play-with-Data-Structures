package cn.mtianyan.leetcode_350;

import java.util.ArrayList;
import java.util.TreeMap;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 遍历nums1
        for (int num : nums1) {
            // 如果map中不包含nums1的数字。
            if (!map.containsKey(num))
                map.put(num, 1); // map中put进该num，并为其计次1
            else
                map.put(num, map.get(num) + 1); // 如果map中已经有该键值，num+1
        }
        // 此时map <1,2> <2,2>
        ArrayList<Integer> list = new ArrayList<>();
        // 遍历nums2
        for (int num : nums2) {
            // 如果map中已经包含该键值，说明是公共键
            if (map.containsKey(num)) {
                list.add(num); // 公共键添加进值列表中 2 2
                map.put(num, map.get(num) - 1); // <1,2> <2,1> | <2,0>
                if (map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}