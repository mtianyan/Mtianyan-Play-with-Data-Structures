package cn.mtianyan.queue;

/**
 * 循环队列实现
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail; // 队首和队尾(下一个空位置索引)
    private int size;        // 可以没有size，自己尝试

    /**
     * 传入容量的构造方法
     *
     * @param capacity
     */
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1]; // 因为会有一个浪费,否则队首和队尾相同时，不知道是空还是满了。
        front = 0;
        tail = 0;
        size = 0;
    }

    /**
     * 默认构造方法，容量为10
     */
    public LoopQueue() {
        this(10);
    }

    /**
     * 获取队列容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 入队方法
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        // 判满，扩容
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 改变容量方法，判满触发，删除时看利用空间是否小于1/4，缩一半。改变时会将队首对齐到0。
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length]; // 此时在newData中将队首对齐到0，data中就得有一个front的偏移量
        }
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 出队方法
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        // 不能缩到0，构造函数new会报错。
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        return data[front];
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
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 判断队列是否为满，要浪费一个位置。
     *
     * @return
     */
    public boolean isFull() {
        return (tail + 1) % data.length == front; // 下一个tail指向front
    }

    /**
     * 遍历打印队列元素
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) // 最后一个元素不要加，
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
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
