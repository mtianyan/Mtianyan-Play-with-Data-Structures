package cn.mtianyan;

import cn.mtianyan.queue.ArrayQueue;
import cn.mtianyan.queue.LoopQueue;
import cn.mtianyan.queue.Queue;

import java.util.Random;

public class MainCompareArrayLoopQueue {
    /**
     * 测试使用q运行optCount个enqueue和deQueue操作所需时间。单位: 秒
     *
     * @param q
     * @param opCount
     * @return
     */
    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE)); // 生成从0到int最大值
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime(); // 纳秒

        return (endTime - startTime) / 1e9;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue出队入队" + opCount + "次的时间: " + time1 + "秒");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("loopQueue出队入队" + opCount + "次的时间: " + time2 + "秒");
    }
}
