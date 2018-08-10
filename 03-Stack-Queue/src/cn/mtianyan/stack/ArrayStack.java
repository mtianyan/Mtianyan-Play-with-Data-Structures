package cn.mtianyan.stack;

import cn.mtianyan.array.Array;

/**
 * 使用动态数组实现栈
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    /**
     * 传入容量的构造方法
     *
     * @param capacity 容量
     */
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 默认无参构造
     */
    public ArrayStack() {
        array = new Array<>();
    }

    /**
     * 获取容量
     *
     * @return 容量值
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 获取当前栈长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 传入元素入栈,栈顶(数组尾部)入栈
     *
     * @param e
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 栈顶元素出栈,(数组尾部) 出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 获取栈顶元素
     *
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 打印Stack对象及内部元素信息。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack ：");
        res.append("[ ");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

}
