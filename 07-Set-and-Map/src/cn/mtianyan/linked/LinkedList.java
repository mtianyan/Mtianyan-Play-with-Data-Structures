package cn.mtianyan.linked;

public class LinkedList<E> {

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


    private Node dummyHead; // 虚拟头结点
    private int size; // 链表元素个数

    /**
     * 链表默认构造函数，空链表是包含一个虚拟头结点的。
     */
    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }


    /**
     * 从数组创建链表的方法。
     *
     * @param arr
     */
    public LinkedList(E[] arr) {
        this(); // 创建出虚拟头结点
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");
        Node cur = new Node(arr[0]); // 当前节点是真实头结点
        dummyHead.next = cur; // 将虚拟头结点与真实头结点连接
        size++;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
            size++;
        }
    }

    /**
     * 获取链表中元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表的index(0-based)位置添加新的元素e
     * 在链表中不是一个常用的操作，练习题用,面试用。
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        // index可以取到size，在链表末尾空位置添加元素。
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index");
        }
        Node prevNode = dummyHead;
        // 因为有了dummyHead，多遍历一次，遍历index次
        for (int i = 0; i < index; i++) {
            // 验证。 12 index 1添加，index-1=0一次也不执行，正好是head。符合
            // 验证。 1234 index 2添加，index-1=1 运行一次pre指向head下一个也就是2，符合。
            prevNode = prevNode.next;
        }
        //        Node insertNode = new Node(e);
        //        insertNode.next = prevNode.next;
        //        prevNode.next = insertNode;
        prevNode.next = new Node(e, prevNode.next); // 后半截是前两句完成任务

        size++;
    }

    /**
     * 在链表头添加新元素e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表末尾添加新的元素e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表的第index(0-based)位置元素
     * 链表中不是常用操作，练习用
     *
     * @param index
     * @return
     */
    public E get(int index) {
        // index不可以取到size，索引从0开始，最多取到size-1
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index");
        }
        Node cur = dummyHead.next; // 从索引为0元素开始
        // 下面与找index-1个节点保持一致。上面执行了一次。所以从index-1个元素变成了找index个元素。
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;

    }

    /**
     * 获取链表中第一个元素(index 0)
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表中最后一个元素(index size-1)
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表的第index(0-based)个位置的元素为e
     * 在链表中不是一个常用的操作，练习用
     */
    public void set(int index, E e) {
        // index不可以取到size，索引从0开始，最多取到size-1
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index");
        }
        Node cur = dummyHead.next; // 从索引为0元素开始
        // 下面与找index-1个节点保持一致。上面执行了一次。所以从index-1个元素变成了找index个元素。
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素e
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除链表中指定index位置的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;

        size--;

        return retNode.e;
    }

    /**
     * 删除第一个元素(index 0)
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素(index 1)
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素e
     *
     * @param e
     */
    public void removeElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    /**
     * 打印链表元素信息
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null){
//            res.append(cur.e +"->");
//            cur = cur.next;
//        }
//        res.append("NULL");
        res.append("head: ");
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur.e + "->");
        }
        res.append("NULL");
        return res.toString();
    }
}
