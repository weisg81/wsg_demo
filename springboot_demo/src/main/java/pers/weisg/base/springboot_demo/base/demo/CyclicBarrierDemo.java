package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author weisg
 * @description CyclicBarrier(循环栅栏)： CyclicBarrier 和 CountDownLatch 非常类似，它也可以实现线程间的技术等待，
 * 但是它的功能比 CountDownLatch 更加复杂和强大。主要应用场景和 CountDownLatch 类似。
 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，
 * 所有被屏障拦截的线程才会继续干活。CyclicBarrier默认的构造方法是 CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
 * 每个线程调用await方法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
 * @date 2019/11/26 0026
 */
public class CyclicBarrierDemo {
    /**
     * CyclicBarrier 的应用场景
     *
     * CyclicBarrier 可以用于多线程计算数据，最后合并计算结果的应用场景。
     * 比如我们用一个Excel保存了用户所有银行流水，每个Sheet保存一个帐户近一年的每笔银行流水，
     * 现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，
     * 得到每个sheet的日均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水。
     */
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
