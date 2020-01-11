package pers.weisg.base.springboot_demo.base.demo;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description java.lang.OutOfMemoryError: Direct buffer memory
 * Java 允许应用程序通过 Direct ByteBuffer 直接访问堆外内存，许多高性能程序通过 Direct ByteBuffer 结合内存映射文件（Memory Mapped File）实现高速 IO。
 * Direct ByteBuffer 的默认大小为 64 MB，一旦使用超出限制，就会抛出 Direct buffer memory 错误。
 * @date 2019/11/28 0028
 */
public class DirectBufferMemoryDemo {
    /**
     * Java 只能通过 ByteBuffer.allocateDirect 方法使用 Direct ByteBuffer，因此，可以通过 Arthas 等在线诊断工具拦截该方法进行排查。
     * 检查是否直接或间接使用了 NIO，如 netty，jetty 等。
     * 通过启动参数 -XX:MaxDirectMemorySize 调整 Direct ByteBuffer 的上限值。
     * 检查 JVM 参数是否有 -XX:+DisableExplicitGC 选项，如果有就去掉，因为该参数会使 System.gc() 失效。
     * 检查堆外内存使用代码，确认是否存在内存泄漏；或者通过反射调用 sun.misc.Cleaner 的 clean() 方法来主动释放被 Direct ByteBuffer 持有的内存空间。
     * 内存容量确实不足，升级配置。
     */
    public static void main(String[] args) {
        ;
        System.out.println("配置maxDirectMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try { TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
        //-XX:MaxDirectMemorySize=5m
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
    }
}

/**
 * Unable to create new native thread
 * 每个 Java 线程都需要占用一定的内存空间，当 JVM 向底层操作系统请求创建一个新的 native 线程时，如果没有足够的资源分配就会报此类错误。
 */

/**
 * 原因分析
 * JVM 向 OS 请求创建 native 线程失败，就会抛出 Unable to create new native thread，常见的原因包括以下几类：
 *
 * 线程数超过操作系统最大线程数 ulimit 限制。
 * 线程数超过 kernel.pid_max（只能重启）。
 * native 内存不足。
 * 该问题发生的常见过程主要包括以下几步：
 *
 * JVM 内部的应用程序请求创建一个新的 Java 线程；
 * JVM native 方法代理了该次请求，并向操作系统请求创建一个 native 线程；
 * 操作系统尝试创建一个新的 native 线程，并为其分配内存；
 * 如果操作系统的虚拟内存已耗尽，或是受到 32 位进程的地址空间限制，操作系统就会拒绝本次 native 内存分配；
 * JVM 将抛出 java.lang.OutOfMemoryError: Unable to create new native thread 错误。
 */

/**
 * 解决方案
 * 升级配置，为机器提供更多的内存；
 * 降低 Java Heap Space 大小；
 * 修复应用程序的线程泄漏问题；
 * 限制线程池大小；
 * 使用 -Xss 参数减少线程栈的大小；
 * 调高 OS 层面的线程最大数：执行 ulimia -a 查看最大线程数限制，使用 ulimit -u xxx 调整最大线程数限制。
 */
