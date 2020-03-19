package pers.weisg.base.springboot_demo.base.demo;

/**
 * Description 枚举类（线程安全，调用效率高，不能延时加载，可以天然的防止反射和反序列化调用）
 * Author WEISANGENG
 * Date 2020/3/19
 **/
public enum SingletonDemo3 {

    //枚举元素本身就是单例
    INSTANCE;

    //添加自己需要的操作
    public void singletonOperation(){
        System.out.println("dosomething...........");
    }
}
