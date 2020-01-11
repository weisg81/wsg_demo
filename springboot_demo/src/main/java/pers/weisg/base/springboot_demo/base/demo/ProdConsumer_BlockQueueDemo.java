package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResouce{
    private volatile boolean flag = true;//默认开启，进行生产+消息
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResouce(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while(flag){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            try { TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止生产 flag = false");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while(flag){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result == null || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒未能消费，消费退出");
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop() throws  Exception{
        this.flag = false;
    }
}
/**
 * @author weisg
 * @description volatile/CAs/atomicInteger/BLockQueue/线程交互/原子引用
 * @date 2019/11/26 0026
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResouce myResouce = new MyResouce(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResouce.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResouce.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try { TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){ e.printStackTrace();}

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到，生产消费时间到");
        try {
            myResouce.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
