package cn.mtianyan.heap;

import cn.mtianyan.array.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    /**
     * 带参构造函数，传入容量，调用带容量的数组构造
     *
     * @param capacity
     */
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * 默认无参构造函数
     */
    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将任意数组整理成堆的形状
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    /**
     * 返回堆中元素个数
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 计算传入的index的父亲节点index值
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index 0 doesn't have parent.");
        return (index - 1) / 2;
    }

    /**
     * 计算传入的index的左孩子index值
     *
     * @param index
     * @return
     */
    private int left(int index) {
        return (2 * index + 1);
    }

    /**
     * 计算传入的index的右孩子节点index值
     *
     * @param index
     * @return
     */
    private int right(int index) {
        return (2 * index + 2);
    }

    /**
     * 堆中添加元素方法。
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * index 为i位置元素上浮。
     *
     * @param i
     */
    private void siftUp(int i) {
        // 当上浮元素大于父亲，继续上浮。并且不能上浮到0之上
        // 直到i 等于 0 或 比 父亲节点小了。
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) > 0) {
            // 数组Array中添加方法swap
            data.swap(i, parent(i));
            i = parent(i); // 这句话让i来到新的位置，使得循环可以查看新的位置是否还要大。
        }
    }


    /**
     * 看堆中的最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {

        E ret = findMax();

        data.swap(0, data.getSize() - 1); // 0位置元素和最后一个元素互换。
        data.removeLast(); // 删除此时的最后一个元素(最大值)
        siftDown(0); // 对于0处进行siftDown操作

        return ret;
    }

    /**
     * k位置元素下移
     *
     * @param k
     */
    private void siftDown(int k) {

        // k节点已经是叶子节点没有孩子了肯定不用下沉了。k的左孩子索引=size时已经越界了，肯定没有值了。
        while (left(k) < data.getSize()) {
            int j = left(k); // 在此轮循环中,data[k]和data[j]交换位置
            // 右孩子有可能不存在，条件: 有右孩子，右孩子的值大于左孩子的值。
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j++; // 此时j因为++了，存储的是右孩子的索引了。
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if (data.get(k).compareTo(data.get(j)) >= 0)
                break; // 大于时没有违反堆的性质，结束。

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最大元素，并且替换成元素e
     *
     * @param e
     * @return
     */
    public E replace(E e) {

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
