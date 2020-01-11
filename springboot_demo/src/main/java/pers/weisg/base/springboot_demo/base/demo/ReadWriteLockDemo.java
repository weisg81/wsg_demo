package pers.weisg.base.springboot_demo.base.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    //private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        rw.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
        try {
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rw.writeLock().unlock();
        }
    }

    public void get(String key){
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取："+key);
            try {
                //TimeUnit.MICROSECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成,value="+obj);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rw.readLock().unlock();
        }

    }
}

/**
 * @author weisg
 * @description
 *  多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取其享资源应该可以同时进行。
 *  但是如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 *  小总结：
 *      读读能共存
 *      读-写不能共存
 *      写-写不能其存
 *
 *      写操作：原子+独占 整个过程必须是一个完整的统一体，中间不许被分割，被打断
 * @date 2019/11/25 0025
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"", tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
