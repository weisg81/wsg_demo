package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author weisg
 * @description
 *  自旋锁好处：循环比较获取直到成功为止，没有类wait的阻塞。
 *
 *  通过c蛋操作完成自旋锁，A线程先进来调用myLock方法自己持有锁s秒钟，
 *  B随后进来后发现当前有线程持有锁，不是nulL，所以只能通过自旋等待，直动释放锁后品随后抢到。
 * @date 2019/11/25 0025
 */
public class SplinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in....");
        while(!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        SplinLockDemo splinLockDemo = new SplinLockDemo();
        //splinLockDemo.myLock();
        new Thread(() -> {
            splinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splinLockDemo.myUnLock();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            splinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splinLockDemo.myUnLock();
        },"B").start();
    }
}
