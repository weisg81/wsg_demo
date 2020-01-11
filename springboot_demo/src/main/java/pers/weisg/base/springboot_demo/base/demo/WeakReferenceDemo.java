package pers.weisg.base.springboot_demo.base.demo;

import java.lang.ref.WeakReference;

/**
 * @author weisg
 * @description 弱引用
 * @date 2019/11/27 0027
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(weakReference.get());
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC
    }
}
