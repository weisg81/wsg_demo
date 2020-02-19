package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description 一个计数信号量
 *              在概念上，信号量维持一组许可证。 如果有必要，每个acquire()都会阻塞，直到许可证可用，然后才能使用它。
 *              每个release()添加许可证，潜在地释放阻塞获取方。 但是，没有使用实际的许可证对象;
 *              Semaphore只保留可用数量的计数，并相应地执行。
 *              Semaphore：实现一个限流器
 * @date 2019/11/26 0026
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //Semaphore(信号量)-允许多个线程同时访问：
        //synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，
        // Semaphore(信号量)可以指定多个线程同时访问某个资源。
        Semaphore semaphore = new Semaphore(3);// 模拟3个停车位
        for (int i = 1; i <= 6; i++) {// 模拟6部汽车
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    try { TimeUnit.SECONDS.sleep(3);} catch(InterruptedException e){ e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
