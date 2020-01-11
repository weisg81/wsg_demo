package pers.weisg.base.springboot_demo.base.demo;

/**
 * Description 这个类作用是什么
 * Author WEISANGENG
 * Date 2019/11/24
 **/
public class SingletonDemo {

    /**
     * DCL（双端检锁）机制不一定线程安全，原因是有指令重排序的存在，
     * 加入volatile可以禁止指令重排原因在于某一个线程执行到第一次检测，
     * 读取到的instance不为nu时，instance的引用对象可能没有完成初始化。
     */
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t SingletonDemo 构造方法");
    }

    // DCL (Double Check Lock双端检锁机制)
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    /**
                     * instance=new SingletonDemo（）；
                     * 可以分为以下3步完成（伪代码）memory=allgcate（）；
                     * 1.分配对象内存空间instance（memory）；
                     * 2.初始化对象instance=memory；
                     * 3.设置instance指向刚分配的内存地址，此时instance！=null
                     * 步骤2和步骤3不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变
                     */
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
