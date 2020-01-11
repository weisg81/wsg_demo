package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;
    public HoldLockThread(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得===："+lockB);
            try { TimeUnit.SECONDS.sleep(3);} catch(InterruptedException e){ e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}

/**
 * @author weisg
 * @description 死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象 deadlock.
 * @date 2019/11/27 0027
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockB,lockA),"BB").start();
        new Thread(new HoldLockThread(lockA,lockB),"AA").start();

    }
}
