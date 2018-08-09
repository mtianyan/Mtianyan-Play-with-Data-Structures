package cn.mtianyan;

import java.util.Arrays;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail; // 队首和下一个空位置索引
    private int size;        // 可以没有size，自己尝试

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity+1]; // 因为会有一个浪费
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity +1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i+front) % data.length]; // 此时在newData中队首对齐回来，data中就得有一个front的偏移量
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
       if (isEmpty()){
           throw new IllegalArgumentException("Cannot dequeue from an empty queue");
       }
       E ret = data[front];
       data[front] = null;
       front = (front+1) % data.length;
       size--;
       if(size == getCapacity()/4 && getCapacity()/2 != 0){
           resize(getCapacity()/2);
       }
       return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }
    public boolean isFull(){
        return (tail +1)%data.length ==front; // 下一个tail指向front
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i+1)%data.length) {
            res.append(data[i]);
            if ((i+1)%data.length !=tail) // 最后一个元素不要加，
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
            if(i %3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
