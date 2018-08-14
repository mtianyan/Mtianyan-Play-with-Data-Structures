package cn.mtianyan.segment;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data; // 存储整个线段树数据副本
    private Merger<E> merger; // 用户可以传入合并规则

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1); // 根节点索引0，区间左右端点。
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        // 递归到底了。
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 要知道左右子树相应的区间范围。
        // int mid = (l + r) / 2; 整型异常可能
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 业务相关的值合并
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取数组大小
     *
     * @return
     */
    public int getSize() {
        return data.length;
    }

    /**
     * 传入index获取该位置数据
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 返回区间[queryL, queryR]的值
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
     *
     * @param treeIndex 我们都传入了这个treeIndex的区间范围l r; 完全可以包装成一个线段树中的节点类，每个节点存储它所处的区间范围。
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 节点左边界和右边界都与想要查找的重合。
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 用户关注的区间和左孩子一点关系没有
        if (queryL >= mid + 1)
            // 去右子树查找
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            // 用户关注区间和右边没有关系
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        // 一部分左，一部分右 queryL R 被一分为二
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将index位置的值，更新为e
     * @param index
     * @param e
     */
    public void set(int index, E e){

        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e; // index位置换新值
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以treeIndex为根的线段树中更新index的值为e
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e){

        if(l == r){
            tree[treeIndex] = e;
            return;
        }
        // 找index对应的叶子
        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }
    /**
     * 遍历打印树中节点中值信息。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}

