package pers.weisg.base.springboot_demo.base.demo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;
    public void addT60(){
        this.number = 60;
    }

    public  void addPlusPlus(){
        number ++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * Description 验证volatile的可见性
 * 1.1 假如int number = 0; number变量之前根本没有添加volatile关键字修饰
 * 1.2 volatile,可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 *
 * 3 如何解决原子性
 *  * 加synchronized
 *  * 使用juc下的AtomicInteger
 *
 * Author WEISANGENG
 * Date 2019/11/24
 **/
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //需要等待上面的线程全部计算完成后，再用main线程取得最终的结果值看多少
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t atomicInteger value:"+myData.atomicInteger);

    }

    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT60();
            System.out.println(Thread.currentThread().getName()+"\t update number value="+myData.number);

        },"AAA").start();

        //new Thread().start();
        while(myData.number == 0){

        }

        System.out.println(Thread.currentThread().getName()+"\t  mission is over,main get number value:"+myData.number);
    }


}
