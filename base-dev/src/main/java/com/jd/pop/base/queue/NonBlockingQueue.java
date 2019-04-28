
package com.jd.pop.base.queue;

import java.util.PriorityQueue;

/**
 * @description 非阻塞队列 https://blog.csdn.net/weixin_38336658/article/details/80759666
 * @author: qiw-a
 * @create: 2019年4月28日11:05:59
 **/
public class NonBlockingQueue {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    public static void main(String[] args) {
        NonBlockingQueue test = new NonBlockingQueue();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次移走队首元素
                    queue.poll();
                    queue.notify();
                    System.out.println("从队列取走一个元素，队列剩余" +
                            queue.size() + "个元素");
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次插入一个元素
                    queue.offer(1);
                    queue.notify();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" +
                            (queueSize - queue.size()));
                }
            }
        }
    }
}
