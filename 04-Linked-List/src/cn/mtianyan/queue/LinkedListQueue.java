package cn.mtianyan.queue;

/**
 * 链表实现队列接口(为原本队列添加了tail优化队尾入队性能)
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {


    /**
     * Node 节点类(private 不被用户感知)
     */
    private class Node {
        public E e;
        public Node next; // c++实现时是指针

        /**
         * 传入元素e以及next节点的构造函数
         *
         * @param e
         * @param next
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        /**
         * 只传入元素e,next置空。构造函数
         *
         * @param e
         */
        public Node(E e) {
            this.e = e;
            this.next = null;
        }


        /**
         * 默认构造函数，节点元素，next均为空。
         */
        public Node() {
            this(null, null);
        }

        /**
         * 打印节点元素信息。
         *
         * @return
         */
        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail; // 头结点，尾结点指向最后一个非空元素。
    private int size;        // 队列大小

    /**
     * 默认构造函数，头尾置空。
     */
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 获取队列元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队操作
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        // 队尾入队，如果队尾为空，说明这是个空队列。(tail指向最后一个位置的非空元素)
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        }
        // 队列已经有元素了。
        else {
            tail.next = new Node(e); // 先把元素链上来
            tail = tail.next;        // tail后移
        }
        size++;
    }

    /**
     * 出队操作
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node retNode = head;  // retNode为要返回的删除节点head
        head = head.next;     // head后移
        retNode.next = null;  // retNode脱离链条
        // 如果此时head指向空了，队列为空，tail head置空。
        if (head == null)
            tail = null;
        size--;
        return retNode.e;
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e; // 返回头结点值
    }

    /**
     * 遍历打印Queue中节点信息。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
