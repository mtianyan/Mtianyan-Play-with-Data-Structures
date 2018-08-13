package cn.mtianyan.leetcode_347;

import cn.mtianyan.queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

class Solution {
    private class Freq implements Comparable<Freq> {

        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        /**
         * 定义比较规则，频次越低优先级越高。
         *
         * @param another
         * @return
         */
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1; // 当前元素比传进来的元素要大的话，返回1。这是正常大则优先级高，我们这里颠倒一下即可。
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            // 还没有存够k个
            if (pq.getSize() < k)
                pq.enqueue(new Freq(key, map.get(key)));
                // 替换频次最小的那个
            else if (map.get(key) > pq.getFront().freq) {
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty())
            res.add(pq.dequeue().e);
        return res;
    }

    private static void printList(List<Integer> nums) {
        for (Integer num : nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new Solution()).topKFrequent(nums, k));
    }
}