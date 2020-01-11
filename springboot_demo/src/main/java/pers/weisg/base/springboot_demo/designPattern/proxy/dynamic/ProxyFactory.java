package pers.weisg.base.springboot_demo.designPattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/27 0027
 */
public class ProxyFactory {

    //维护一个目标对象 , Object
    private Object target;

    //构造器 ， 对target 进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象 生成一个代理对象
    public Object getProxyInstance(){
        /**
         * newProxyInstance(ClassLoader loader, //the class loader to define the proxy class
         *                                      //指定当前目标对象使用的类加载器, 获取加载器的方法固定
         *                  Class<?>[] interfaces,//the list of interfaces for the proxy class to implement
         *                                        //目标对象实现的接口类型，使用泛型方法确认类型
         *                  InvocationHandler h)  //the invocation handler to dispatch method invocations to
         *                                        //事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行的目标对象方法作为参数传入
         */
        /*return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("JDK代理开始~~");
                //反射机制调用目标对象的方法
                Object invokeObj = method.invoke(target, args);
                System.out.println("JDK代理提交");
                return invokeObj;
            }
        });*/

        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),(proxy, method, args)->{
            System.out.println("JDK代理开始~~");
            //反射机制调用目标对象的方法
            Object invokeObj = method.invoke(target, args);
            System.out.println("JDK代理提交");
            return invokeObj;
        });

    }
}
