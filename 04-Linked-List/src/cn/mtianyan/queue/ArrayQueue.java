package cn.mtianyan.queue;

import cn.mtianyan.array.Array;

/**
 * 队列: 使用动态数组实现队列接口
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    /**
     * 传入容量构造方法
     *
     * @param capacity
     */
    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    /**
     * 默认构造方法，默认容量为10
     */
    public ArrayQueue() {
        this.array = new Array<>();
    }

    /**
     * 获取容量方法。数组实现的队列特有方法
     *
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 队列入队方法，队尾入队。
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 队列出队方法，队首出队
     *
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 获取队列长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 遍历打印队列中元素。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue ：");
        res.append("front [ ");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        queue.dequeue();
        System.out.println(queue);
    }
}
