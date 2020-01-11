package pers.weisg.base.springboot_demo.base.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author weisg
 * @description 虚引用
 *  java是供了4种引用类型，在垃级回收的时候，都有自己各自的特点。
 *  ReferenceQueue是用来配合引用工作的，ReferenceQueue一样可以运行。
 *
 *  创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，
 *  如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动，
 *  这相当于是一种通知机制。
 *
 *  当关联的引用成列中有数据的时候，就味着引用指向的维内存中的对象被回收。通过这种方式，JVm允许我们在对象被消酸后，做一些我们自己想做的非情。
 * @date 2019/11/28 0028
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj1, referenceQueue);
        System.out.println(obj1);
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference.get());
        System.out.println("=================================");


        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference.get());
    }
}
