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
        /**
         * 从字面上理解，CountDown表示减法计数，Latch表示门闩的意思，计数为0的时候就可以打开门闩了。
         * Cyclic Barrier表示循环的障碍物。两个类都含有这一个意思：对应的线程都完成工作之后再进行下一步动作，
         * 也就是大家都准备好之后再进行下一步。然而两者最大的区别是，进行下一步动作的动作实施者是不一样的。
         * 这里的“动作实施者”有两种，一种是主线程（即执行main函数），另一种是执行任务的其他线程，
         * 后面叫这种线程为“其他线程”，区分于主线程。对于CountDownLatch，当计数为0的时候，
         * 下一步的动作实施者是main函数；对于CyclicBarrier，下一步动作实施者是“其他线程”。
         */
        //CountDownLatch和CyclicBarrier有什么区别呢
        //CountDownLatch只能使用一次，而CyclicBarrier方法可以使用reset()方法重置，
        // 所以CyclicBarrier方法可以处理更为复杂的业务场景。
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
