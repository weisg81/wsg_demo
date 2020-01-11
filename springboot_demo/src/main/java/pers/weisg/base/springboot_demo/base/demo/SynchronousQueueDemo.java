package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/26 0026
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 11");
                blockingQueue.put("11");
                System.out.println(Thread.currentThread().getName()+"\t put 22");
                blockingQueue.put("22");
                System.out.println(Thread.currentThread().getName()+"\t put 33");
                blockingQueue.put("33");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){ e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){ e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){ e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
