package cn.mtianyan;

import cn.mtianyan.queue.ArrayQueue;
import cn.mtianyan.queue.LinkedListQueue;
import cn.mtianyan.queue.LoopQueue;
import cn.mtianyan.queue.Queue;

import java.util.Random;

/**
 * 比较ArrayQueue，LoopQueue，LinkedListQueue的性能差异。
 *
 * ArrayQueue的test方法是O(n^2)级别 队列
 * LoopQueue和LinkedList的test都是O(n)级别
 * ArrayQueue, time: 286.450278485 s
 * LoopQueue, time: 0.050651608 s
 * LinkedListQueue, time: 0.037897358 s
 * 可以看到巨大的性能差异。
 */
public class ThreeCompareArrayLoopLinkedQueue {

    // 测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e9;
    }

    public static void main(String[] args) {

        int opCount = 1000000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }
}
