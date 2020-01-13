package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/26 0026
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier方法可以使用reset()方法重置，所以CyclicBarrier方法可以处理更为复杂的业务场景
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("*************召唤神龙。。。");
        });
        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 收集到第："+tempInt+"龙珠。");
                try {
                    cyclicBarrier.await();//到达屏障
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
