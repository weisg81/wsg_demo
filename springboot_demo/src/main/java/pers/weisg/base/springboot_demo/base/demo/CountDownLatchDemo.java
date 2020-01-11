package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/25 0025
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CounttryEnum.getMsgByCode(i)).start();
        }
        countDownLatch.await();
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
