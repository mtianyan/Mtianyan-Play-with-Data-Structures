package cn.mtianyan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树，binary search tree（限制类型具有可比较性）
 */
public class BST<E extends Comparable<E>> {
    /**
     * 节点类对用户屏蔽
     */
    private class Node {
        public E e; // 节点元素值
        public Node left, right; // 左子树，右子树引用

        /**
         * 默认的节点构造函数
         *
         * @param e
         */
        public Node(E e) {
            this.e = e;
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
     * 向二分搜索树中添加新的元素e(面向用户使用的)
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 返回插入新节点后二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        // 上面条件不满足，说明还得继续往下找左右子树为null可以挂载上的节点
        if (e.compareTo(node.e) < 0)
            // 如果e小于node.e，那么继续往它的左子树添加该节点,这里插入结果可能根发生了变化。
            node.left = add(node.left, e); // 新节点赋值给node.left,改变了二叉树
        else if (e.compareTo(node.e) > 0)
            // 大于，往右子树添加。
            node.right = add(node.right, e);
        return node;
    }

    /**
     * 看二分搜索树中是否包含元素e（面向用户的）
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e(递归算法语义)
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) < 0
            return contains(node.right, e);
    }

    /**
     * 二分搜索树的前序遍历（用户使用）
     */
    public void preOrder() {
        preOrder(root);
    }


    /**
     * 前序遍历以node为根的二分搜索树, 递归算法
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的中序遍历（用户使用）
     */
    public void inOrder() {
        inOrder(root);
    }


    /**
     * 中序遍历以node为根的二分搜索树, 递归算法
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    /**
     * 二分搜索树的后序遍历（用户使用）
     */
    public void postOrder() {
        postOrder(root);
    }


    /**
     * 后序遍历以node为根的二分搜索树, 递归算法
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 二分搜索树的非递归中序遍历
     */
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.empty()) {

            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.print(cur.e + " ");
            cur = cur.right;
        }
    }

    /**
     * 二分搜索树的非递归后序遍历
     */
    public void postOrderNR() {

        Stack<Node> stack = new Stack<>();
        Node pre = null;
        Node cur = root;

        while (cur != null || !stack.empty()) {

            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.right == null || pre == cur.right) {
                    System.out.printf(cur.e + " ");
                    pre = cur;
                    cur = null;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
    }

    /**
     * 二分搜索树的层序遍历，使用队列实现
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.printf(cur.e + " ");

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 寻找二分搜索树的最小元素(面向用户)
     *
     * @return
     */
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
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
     * 寻找二分搜索树的最大元素（面向用户）
     *
     * @return
     */
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 返回最小值
     *
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点 返回删除节点后新的二分搜索树的根
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

    /**
     * 从二分搜索树中删除最大值所在节点
     *
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }


    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {

        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
     * 打印二分搜索树的信息
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     *
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    /**
     * 生成树深度的标识。
     *
     * @param depth
     * @return
     */
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
