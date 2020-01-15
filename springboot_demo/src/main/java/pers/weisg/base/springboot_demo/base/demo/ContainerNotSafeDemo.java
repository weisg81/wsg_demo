package pers.weisg.base.springboot_demo.base.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description 集合类不安全问题
 * @date 2019/11/25 0025
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        /**
         * SynchronizedSet.synchronizedSet(Set<T> s)
         * SynchronizedSet.synchronizedList(List<T> list)
         * SynchronizedSet.synchronizedMap(Map<K,V> m)
         * 同步容器的实现原理就是在容器的操作方法上，加上了 synchronized 关键字。
         */
        /**
         * jdk 1.5后
         * List：CopyOnWriteArrayList
         * Set：CopyOnWriteArraySet、ConcurrentSkipListSet(1.6)
         * Map：ConcurrentHashMap、ConcurrentSkipListMap(1.6)
         *
         * 单端阻塞队列：ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue、LinkedTransferQueue、PriorityBlockingQueue、DelayQueue
         * 双端阻塞队列：LinkedBlockingDeque
         * 单端非阻塞队列：ConcurrentLinkedQueue
         * 双端非阻塞队列：ConcurrentLinkedDeque
         */

        //Map<String, String> map = new HashMap<>();
        //Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        //
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        new HashSet<>();
    }

    private static void listNotSafe() {
        //List<String> list = new ArrayList<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        //
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException

        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         * 2 导致原因
         *      并发年争抢修改导致，
         * 3 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>()); //Collections.synchronizedMap(new HashMap<>());
         *      3.3 new CopyOnWriteArrayList<>();
         *
         * 4 优化建议
         */}
}
