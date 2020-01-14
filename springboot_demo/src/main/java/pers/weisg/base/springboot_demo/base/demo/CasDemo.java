package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description CAS demo
 * Author WEISANGENG
 * Date 2019/11/24
 **/
public class CasDemo {
    /**
     * 1 Unsafe
     *  是CAS的核心类，由于Java方法无法直接访问底层系统，
     *  需要通过本地（native）方法来访问，Unsafe相当于一个后门，
     *  基于该类可以直接操作特定内存的数据。Unsafe类存在于sun.misc包中，
     *  其内部方法操作可以像C的指针一样直接操作内存，
     *  因为Java中CAS操作的执行依赖于Unsafe类的方法。
     *
     *  注意Unsafe类中的所有方法都是native 修饰的，
     *  也就是说Unsafe类中的方法都直接湖用操作系统底层资源热金相应在务
     *
     *  CAS（CompareAndSwap）
     *      比较当前工作内存中的值和主内存中的值，
     *      如果相同则执行规定操作，否则继续比较直到主内存和工作内存中的值一致为止.
     *
     *  缺点：
     *      循环时间长开销很大
     *          如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大的开销。
     *      只能保证一个共享变量的原子操作。AQS
     *      引出来ABA问题-》原子引用更新
     *
     *
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger  = new AtomicInteger(6);
        System.out.println(atomicInteger.compareAndSet(6, 2020)+"\t current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020, 5)+"\t current data:"+atomicInteger.get());
    }
}
