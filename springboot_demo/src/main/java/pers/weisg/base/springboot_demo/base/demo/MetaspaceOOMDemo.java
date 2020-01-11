package pers.weisg.base.springboot_demo.base.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
public class MetaspaceOOMDemo {

    static class OOMDemo{}

    public static void main(String[] args) {//org.springframework.cglib.core.CodeGenerationException: java.lang.OutOfMemoryError-->Metaspace
        // -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
        int i = 0;//模拟计数多少次以后发生异常
        try {
            while(true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMDemo.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("多少次后发生异常。。。。。i="+i);
            e.printStackTrace();
        }

    }
}
