package cn.mtianyan.map;

import cn.mtianyan.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {
    /**
     * Node 节点类(private 不被用户感知) For Map
     */
    private class Node {
        public K key;
        public V value;
        public Node next; // c++实现时是指针

        /**
         * 传入key和value以及next节点的构造函数
         *
         * @param key
         * @param value
         * @param next
         */
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * 只传入key,next置空。构造函数
         *
         * @param key
         */
        public Node(K key) {
            this.key = key;
            this.next = null;
        }


        /**
         * 默认构造函数，节点元素，next均为空。
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * 打印节点元素信息。
         *
         * @return
         */
        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead; // 虚拟头结点
    private int size; // 链表元素个数

    /**
     * LinkedListMap构造函数
     */
    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
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
     * 辅助函数，给key，返回该节点
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }


    /**
     * 传入key 返回是否包含该节点
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 传入key，返回value。如果key不存在返回null
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 添加key与value，已存在的key，更新value
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        // 去重
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 设计上如果传入已存在的key，更新value
            node.value = value;
        }
    }

    /**
     * 更新key对应的value值
     *
     * @param key
     * @param newValue
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        node.value = newValue;
    }

    /**
     * 删除key对应的节点
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
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
