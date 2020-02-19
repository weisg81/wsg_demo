package pers.weisg.base.springboot_demo.base.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/27 0027
 */
public class HelloGC {

    public static void main(String[] args) {
        /**
         * 常用收集器分类如下：
         *
         * 串行收集器：Serial，Serial Old
         * 并行收集器：ParNew，Parallel Scavenge，Parallel Old
         * 并发收集器：CMS，G1
         *
         * 一般jvm调优可以从三个方面去调优
         * 1、调整新生代的大小到最合适和老年代的大小最合适（上线的时候）
         * 2、选择合适的GC回收器
         * 3、代码的调优
         *
         * 首先通过在线上服务通过命令行jps -vVml，查找你当前项目的服务的id
         * 然后通过jstat -gc <服务的id>，查看新生代触发的次数，以及垃圾回收所消耗的时间，查看老年代触发的次数，以及垃圾回收所消耗的时间。
         * 并且通过打开GC日记，或者是jmap -heap 查看内存的使用情况，如果是内存导致的问题，可以通过设置jvm的参数提高内存-Xms -Xmx。
         *
         * https://www.cnblogs.com/levontor/p/11340466.html
         * https://blog.csdn.net/qq_42326161/article/details/103861600
         */
        // -XX:MetaspaceSize=64M -XX:+PrintGCDetails
        System.out.println("******hello GC");
        try { TimeUnit.SECONDS.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}

        //-XX:InitialHeapSize=1000M -XX:MetaspaceSize=100M -XX:+PrintGCDetails

        /*long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms)="+totalMemory+"(字节),"+(totalMemory/1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx)="+maxMemory+"(字节),"+(maxMemory/1024/1024)+"MB");*/

        //Byte[] bytes = new Byte[50*1024*1024];// 大对象 java.lang.OutOfMemoryError: Java heap space

        //java -XX:PrintFlagsInitial  查看初始默认参数

        // java -XX:+PrintFlagsFinal -version 查看更新值 在运行中的程序的更新值
        //如：java -XX:+PrintFlagsFinal -XX:MetaspaceSize=512M T1

        //java -XX:+PrintCommandLineFlags -version

        //-Xss 大小 线程堆栈大小（以字节为单位）。附加字母k或K表示KB
        // jinfo -flag ThreadStackSize 15 查看Xss的大小，默认单位是k 在idea里执行结果是0 注意：0 代表系统默认初始值

        //-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=1024m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseParallelGC
        //#-Xms10m -Xmx10m -XX:+PrintGcDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew+Tenured)
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
        //-XX:+UseParallelGC (jdk1.8 默认使用的并行收集器)

        /**
         * 假如16G内存
         *
         */
        /**
         *
         * D:\dev\idea\idea_workspace\wsg\base>jinfo -flag InitialHeapSize  10056
         * -XX:InitialHeapSize=268435456/1024/1024=256M
         * -Xms 初始化内存，默认为物理内存的1/64  当前运行电脑内存为16G
         *
         *信号量
         * D:\dev\idea\idea_workspace\wsg\base>jinfo -flag MaxHeapSize  10056
         * -XX:MaxHeapSize=4263510016 = 4263510016/1024/1024=4066M
         * -Xmx 最大内存，默认为物理内存的1/4 当前运行电脑内存为16G
         *
         * D:\dev\idea\idea_workspace\wsg\base>jinfo -flag NewSize  10056
         * -XX:NewSize=89128960
         *
         *
         *  D:\dev\idea\idea_workspace\wsg\base>jinfo -flag MetaspaceSize 10056
         *  -XX:MetaspaceSize=21807104 = 21807104/1024/1024 = 20.7M
         */

        //-XX:+PrintGCDetails 重点

        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC

        //  -XX:+UseSerialGC -XX:NewRatio=2

        // -XX:MaxTenuringThreshold=20 //MaxTenuringThreshold of 20 is invalid; must be between 0 and 15

        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m




        /**
         * 常用参数配置信息:https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html
         */
        /**
         * -Xms 大小
         * 设置堆的初始大小（以字节为单位）。此值必须是1024的倍数且大于1 MB。追加字母k或K表示千字节，m或M表示兆字节，g或G表示千兆字节。
         *
         * 以下示例显示如何使用各种单位将分配的内存大小设置为6 MB：
         *
         * -Xms6291456
         * -Xms6144k
         * -Xms6m
         * 如果未设置此选项，则初始大小将设置为为老一代和年轻一代分配的大小之和。可以使用-Xmn选项或-XX:NewSize选项设置年轻代的堆的初始大小。
         *
         * ----------------------------------------------------------------------
         * -Xmx 大小
         * 指定内存分配池的最大大小（以字节为单位）。此值必须是1024的倍数且大于2 MB。追加字母k或K表示千字节，m或M表示兆字节，g或G表示千兆字节。默认值是在运行时根据系统配置选择的。对于服务器部署，-Xms并-Xmx经常设置为相同的值。请参阅位于的Java SE HotSpot虚拟机垃圾收集优化指南中的“人体工程学”部分http://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/index.html。
         *
         * 下面的示例演示如何使用各种单位将分配的最大内存大小设置为80 MB：
         *
         * -Xmx83886080
         * -Xmx81920k
         * -Xmx80m
         * 该-Xmx选项等效于-XX:MaxHeapSize。
         *
         * -------------------------------------------------------------------------
         * -Xss 大小
         * 设置线程堆栈大小（以字节为单位）。附加字母k或K表示KB，m或M表示MB，g或G表示GB。默认值取决于平台：
         *
         * Linux / ARM（32位）：320 KB
         *
         * Linux / i386（32位）：320 KB
         *
         * Linux / x64（64位）：1024 KB
         *
         * OS X（64位）：1024 KB
         *
         * Oracle Solaris / i386（32位）：320 KB
         *
         * Oracle Solaris / x64（64位）：1024 KB
         *
         * 下面的示例将线程堆栈大小以不同的单位设置为1024 KB：
         *
         * -Xss1分钟
         * -Xss1024k
         * -Xss1048576
         * 此选项等效于-XX:ThreadStackSize。
         *
         *-------------------------------------------------------------------------
         * -XX：NewSize = 大小
         * 为年轻一代（苗圃）设置堆的初始大小（以字节为单位）。追加字母k或K表示千字节，m或M表示兆字节，g或G表示千兆字节。
         *
         * 堆的年轻代区域用于新对象。与其他区域相比，在该区域执行GC的频率更高。如果年轻一代的大小太小，则将执行大量次要GC。如果大小太大，则将仅执行完整的GC，这可能需要很长时间才能完成。Oracle建议您将年轻代的大小保持在整个堆大小的一半到四分之一之间。
         *
         * 以下示例显示如何使用各种单位将年轻一代的初始大小设置为256 MB：
         *
         * -XX：NewSize = 256m
         * -XX：NewSize = 262144k
         * -XX：NewSize = 268435456
         * 该-XX:NewSize选项等效于-Xmn。
         *
         *-------------------------------------------------------------------------
         * -XX：MetaspaceSize = 大小
         * 设置分配的类元数据空间的大小，该类元数据空间将在首次超过垃圾收集时触发垃圾收集。垃圾收集的阈值取决于使用的元数据量而增加或减少。默认大小取决于平台。
         *
         *-------------------------------------------------------------------------
         * -XX：SurvivorRatio = 比率
         * 设置伊甸园空间大小与幸存者空间大小之间的比率。默认情况下，此选项设置为8。以下示例显示如何将eden / survivor空间比率设置为4：
         *
         * -XX：SurvivorRatio = 4
         *
         *-------------------------------------------------------------------------
         * -XX：NewRatio = 比率
         * 设置新老一代大小之间的比率。默认情况下，此选项设置为2。以下示例显示如何将年轻/老人比率设置为1：
         *
         * -XX：NewRatio = 1
         *
         *-------------------------------------------------------------------------
         *
         * -XX：MaxTenuringThreshold = 阈值
         * 设置用于自适应GC大小调整的最大使用期限阈值。最大值为15。并行（吞吐量）收集器的默认值为15，而CMS收集器的默认值为6。
         *
         * 以下示例显示如何将最大期限阈值设置为10：
         *
         * -XX：MaxTenuringThreshold = 10
         *
         *-------------------------------------------------------------------------
         * -XX：+ UseSerialGC
         * 启用串行垃圾收集器的使用。对于不需要垃圾回收具有任何特殊功能的小型和简单应用程序，这通常是最佳选择。默认情况下，此选项是禁用的，并且将根据计算机的配置和JVM的类型自动选择收集器。
         *
         *-------------------------------------------------------------------------
         * -XX：+ UseConcMarkSweepGC
         * 启用CMS垃圾收集器用于旧版本。当吞吐量（-XX:+UseParallelGC）垃圾收集器无法满足应用程序延迟要求时，Oracle建议您使用CMS垃圾收集器。G1垃圾收集器（-XX:+UseG1GC）是另一种选择。
         *
         *
         *-------------------------------------------------------------------------
         * -XX：+ PrintCommandLineFlags
         * 启用打印在命令行上出现的按人体工程学选择的JVM标志。了解JVM设置的人体工程学值（例如堆空间大小和选定的垃圾收集器）可能会很有用。默认情况下，此选项为禁用状态，并且不打印标志。
         */

    }
}
