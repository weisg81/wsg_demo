package pers.weisg.base.springboot_demo.common;

import java.util.concurrent.*;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/2 0002
 */
public class MyThreadPoolUtils {

    public static volatile ExecutorService service = null;



    private static ExecutorService getThreadService(int corePoolSize, int maximumPoolSize){
        /**
         * 1、corePoolSize：核心线程数
         *         * 核心线程会一直存活，及时没有任务需要执行
         *         * 当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
         *         * 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
         *
         *     2、queueCapacity：任务队列容量（阻塞队列）
         *         * 当核心线程数达到最大时，新任务会放在队列中排队等待执行
         *
         *     3、maxPoolSize：最大线程数
         *         * 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
         *         * 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
         *
         *     4、 keepAliveTime：线程空闲时间
         *         * 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
         *         * 如果allowCoreThreadTimeout=true，则会直到线程数量=0
         *
         *     5、allowCoreThreadTimeout：允许核心线程超时
         *     6、rejectedExecutionHandler：任务拒绝处理器
         *         * 两种情况会拒绝处理任务：
         *             - 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
         *             - 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
         *         * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
         *         * ThreadPoolExecutor类有几个内部实现类来处理这类情况：
         *             - AbortPolicy 丢弃任务，抛运行时异常
         *             - CallerRunsPolicy 执行任务 “调用者运行“一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
         *             - DiscardPolicy 忽视，什么都不会发生 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务。
         *             - DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务 直接丢弃任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种方案。
         *         * 实现RejectedExecutionHandler接口，可自定义处理器
         */
        /**
         * corePoolSize:核心线程数
         * maximumPoolSize:最大线程数
         * keepAliveTime:非核心线程外其它线程空闲情况下存活时间
         * unit:keepAliveTime的基本时间单位
         * workQuene:线程工作队列
         * threadFactory:线程创建工厂
         * handler:拒绝策略处理器
         *
         */
        service = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,10*60L,TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(30),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
        return service;
    }

    public static ExecutorService getExecutorService(){
        if(service == null){
            synchronized (MyThreadPoolUtils.class){
                if(service == null){
                    return getThreadService(5,10);
                }
            }
        }
        return null;
    }
}
