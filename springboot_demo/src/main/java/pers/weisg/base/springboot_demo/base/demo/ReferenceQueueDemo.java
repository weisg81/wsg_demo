package pers.weisg.base.springboot_demo.base.demo;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(obj1, referenceQueue);
        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        obj1 = null;
        System.gc();
        //try { TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println("-------------------------------");
        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
