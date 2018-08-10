package cn.mtianyan.queue;

public interface Queue<E> {
    void enqueue(E e); // 入队

    E dequeue();       // 出队

    E getFront();      // 获取队首元素

    int getSize();      // 获取队列元素多少

    boolean isEmpty();  // 是否为空
}
