package cn.mtianyan;

import java.util.ArrayList;

public class BST<K extends Comparable<K>, V> {
    /**
     * 节点类对用户屏蔽
     */
    private class Node {
        public K key; // 节点key
        public V value;
        public Node left, right; // 左子树，右子树引用

        /**
         * 默认的节点构造函数
         *
         * @param key
         * @param value
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;  // 根节点
    private int size;   // 树中元素的个数

    /**
     * 默认的二分搜索树构造函数
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 获取搜索树中节点元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 二分搜索树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树找那个添加新的键值对
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 返回插入新的键值对后二分搜索树的根
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        // 上面条件不满足，说明还得继续往下找左右子树为null可以挂载上的节点
        if (key.compareTo(node.key) < 0)
            // 如果e小于node.e，那么继续往它的左子树添加该节点,这里插入结果可能根发生了变化。
            node.left = add(node.left, key, value); // 新节点赋值给node.left,改变了二叉树
        else if (key.compareTo(node.key) > 0)
            // 大于，往右子树添加。
            node.right = add(node.right, key, value);
            // 如果相等
        else
            node.value = value;
        return node;
    }

    /**
     * 辅助方法: 返回以node为根节点到的二分搜索树中，key所在的节点。
     *
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // >0
            return getNode(node.right, key);
    }

    /**
     * 是否包含传入key所对应的键值对
     *
     * @param key
     * @return
     */
public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 通过key获取对应的value
     *
     * @param key
     * @return
     */

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 更新指定key的value值
     *
     * @param key
     * @param newValue
     */

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist");
        }

        node.value = newValue;
    }

    /**
     * 通过key删除对应的键值对
     *
     * @param key
     * @return
     */

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除掉以node为根的二分搜索树中键为key的节点, 递归算法 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    /**
     * 寻找二分搜索树的最小key(面向用户)
     *
     * @return
     */
    public K minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node minNode = minimum(root);
        return minNode.key;
    }

    /**
     * 返回以node为根的二分搜索树的最小key值所在的节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 从二分搜索树中删除最小key值所在节点, 返回最小值
     *
     * @return
     */
    public K removeMin() {
        K ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小key值节点 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BST<String, Integer> map = new BST<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}

