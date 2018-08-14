package cn.mtianyan.leetcode_307;

/**
 * 使用sum数组的思路：TLE
 * update是O(n)复杂度，sumRange依然是O(1)
 */
class NumArray {

    private int[] data; // 原本的数组备份
    private int[] sum;

    public NumArray(int[] nums) {

        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            data[i] = nums[i];

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    /**
     * update某一个元素的时候，整个数组也会发生变化。
     *
     * @param index
     * @param val
     */
    public void update(int index, int val) {
        data[index] = val;
        // 重建sum数组，从index+1位置后面的都更新一下
        for (int i = index + 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + data[i - 1];
    }
}
