package pers.weisg.base.springboot_demo.base.demo;

/**
 * Description 静态内部类实现模式（线程安全，调用效率高，可以延时加载）
 * Author WEISANGENG
 * Date 2020/3/19
 **/
public class SingletonDemo2 {

    private static class SingletonClassInstance{
        private static final SingletonDemo2 instance=new SingletonDemo2();
    }

    private SingletonDemo2(){
        System.out.println("-------SingletonDemo2--------");
    }

    public static SingletonDemo2 getInstance(){
        return SingletonClassInstance.instance;
    }

    public static void main(String[] args) {
        SingletonDemo2 instance = getInstance();
        System.out.println("============="+instance);
        SingletonDemo3 singletonDemo3 = SingletonDemo3.INSTANCE;
        singletonDemo3.singletonOperation();
        //System.out.println("----------------singletonDemo3="+singletonDemo3.si);
    }
}
