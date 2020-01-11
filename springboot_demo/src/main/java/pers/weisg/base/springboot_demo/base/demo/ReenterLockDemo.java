package pers.weisg.base.springboot_demo.base.demo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t ------------invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * @author weisg
 * @description 可使入锁（也叫做递归级）
 *      指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 *      在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁也即是说，
 *      线程可以进入在何一个它已经拥有的锁所同步着的代码块。
 *
 *      也即是说，线程可以进入在何一个它已经拥有的锁所同步着的代码块。
 * @date 2019/11/25 0025
 */
public class ReenterLockDemo {

    public static void main(String[] args){
        Phone phone = new Phone();
        //phone.sendSMS();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone, "t4");

        t3.start();
        t4.start();

    }
}
