package cn.mtianyan.stack;

import cn.mtianyan.linked.LinkedList;

/**
 * 使用链表实现栈
 *
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    /**
     * 默认构造方法
     */
    public LinkedListStack() {
        list = new LinkedList<>();
    }

    /**
     * 获取栈中元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 返回栈是否空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 从链表头部入栈
     *
     * @param e
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * 从头部出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * 查看栈顶，链表头部
     *
     * @return
     */
    @Override
    public E peek() {
        return list.getFirst();
    }


    /**
     * 打印栈的链表信息
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList Stack ：");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
