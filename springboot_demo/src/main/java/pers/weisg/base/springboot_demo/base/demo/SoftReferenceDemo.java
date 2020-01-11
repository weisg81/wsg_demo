package pers.weisg.base.springboot_demo.base.demo;

import java.lang.ref.SoftReference;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/27 0027
 */
public class SoftReferenceDemo {

    public static void softRef_memory_enough(){
        Object object1 = new Object();//这样定义的默认是强引用
        SoftReference<Object> softReference = new SoftReference<>(object1);
        System.out.println(object1);
        System.out.println(softReference.get());

        object1 = null;
        System.gc();

        System.out.println(object1);
        System.out.println(softReference.get());

    }

    public static void softRef_memory_not_enough(){
        Object object1 = new Object();//这样定义的默认是强引用
        SoftReference<Object> softReference = new SoftReference<>(object1);
        System.out.println(object1);
        System.out.println(softReference.get());

        object1 = null;
        try {
            byte[] bytes = new byte[50*1024*1024];


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(object1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC
        softRef_memory_enough();
        //softRef_memory_not_enough();
    }
}
