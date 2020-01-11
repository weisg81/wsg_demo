package pers.weisg.base.springboot_demo.base.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weisg
 * @description java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 因为垃圾回收为了释放较小的空间而占用了大量时间造成的。通常来说，当程序用98%的时间回收了不到2%的堆内存时导致的。通常是设置的堆内存太小，导致没有足够的内存
 * 当 Java 进程花费 98% 以上的时间执行 GC，但只恢复了不到 2% 的内存，且该动作连续重复了 5 次，就会抛出 java.lang.OutOfMemoryError:GC overhead limit exceeded 错误。
 * 简单地说，就是应用程序已经基本耗尽了所有可用内存， GC 也无法回收。
 * @date 2019/11/28 0028
 */
public class GCOverheadDemo {
    //-Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+UseParallelGC

    /**
     * Java heap space 错误产生的常见原因可以分为以下几类：
     *
     * 请求创建一个超大对象，通常是一个大数组。
     * 超出预期的访问量/数据量，通常是上游系统请求流量飙升，常见于各类促销/秒杀活动，可以结合业务流量指标排查是否有尖状峰值。
     * 过度使用终结器（Finalizer），该对象没有立即被 GC。
     * 内存泄漏（Memory Leak），大量对象引用没有释放，JVM 无法对其自动回收，常见于使用了 File 等资源没有回收。
     */
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true){
              list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("--------------------------i="+i);
        }
    }

    /**
     * 解决方案
     * 针对大部分情况，通常只需要通过 -Xmx 参数调高 JVM 堆内存空间即可。如果仍然没有解决，可以参考以下情况做进一步处理：
     *
     * 如果是超大对象，可以检查其合理性，比如是否一次性查询了数据库全部结果，而没有做结果数限制。
     * 如果是业务峰值压力，可以考虑添加机器资源，或者做限流降级。
     * 如果是内存泄漏，需要找到持有的对象，修改代码设计，比如关闭没有释放的连接。
     */
}
