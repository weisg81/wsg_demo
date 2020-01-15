package pers.weisg.base.springboot_demo.base.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description 队列
 *  阻塞队列
 *      阻塞队列有没有好的一面
 *
 *      不得不阻塞，如何管理
 * ArrayBlockingQueue：是一个基于数组结构的有界用塞队列，此队列按FIFO（先进先出）原则对元系进行排序。
 * LinkedBLockingQueue：一个基于链表结构的阻塞队列，此队列接FIFO（先进先出）排序元素，吞吐量通常要高于ArrayBlockingQueue。
 * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高
 * @date 2019/11/26 0026
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws  Exception{
        //List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        /*System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
        //System.out.println(blockingQueue.add("D"));//java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());//FIFO
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/
        //System.out.println(blockingQueue.remove());//java.util.NoSuchElementException

        /*System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        System.out.println(blockingQueue.offer("D"));
        System.out.println(blockingQueue.peek());*/

        /*System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        System.out.println(blockingQueue.offer("D"));
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        /*blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        System.out.println(blockingQueue);
        //blockingQueue.put("CC");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/
        //blockingQueue.take();

        System.out.println(blockingQueue.offer("A", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("B", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("C", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("D", 2L, TimeUnit.SECONDS));
    }
}
