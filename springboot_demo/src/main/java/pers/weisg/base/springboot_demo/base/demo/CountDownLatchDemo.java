package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/25 0025
 */
public class CountDownLatchDemo {

    //CountDownLatch （倒计时器）： CountDownLatch是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。

    public static void main(String[] args) throws Exception{
        //CountDownLatch和CyclicBarrier有什么区别呢
        //CountDownLatch只能使用一次，而CyclicBarrier方法可以使用reset()方法重置，所以CyclicBarrier方法可以处理更为复杂的业务场景。
        CountDownLatch countDownLatch = new CountDownLatch(6);//CountDownLatch只能使用一次
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CounttryEnum.getMsgByCode(i)).start();
        }
        countDownLatch.await();//阻塞当前线程，直到latch的值为0
        System.out.println(Thread.currentThread().getName()+"\t*****************秦帝国，一统华夏");
    }

    private static void closeDorr() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*****************班长最后关门走人");
    }
}
