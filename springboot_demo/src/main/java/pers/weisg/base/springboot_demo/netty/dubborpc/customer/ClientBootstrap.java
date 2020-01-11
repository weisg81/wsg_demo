package pers.weisg.base.springboot_demo.netty.dubborpc.customer;

import pers.weisg.base.springboot_demo.netty.dubborpc.netty.NettyClient;
import pers.weisg.base.springboot_demo.netty.dubborpc.publicinterface.HelloService;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/16 0016
 */
public class ClientBootstrap {

    //这里定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws Exception{

        //创建一个消费者
        NettyClient customer = new NettyClient();
        HelloService service = (HelloService)customer.getBean(HelloService.class, providerName);
        for (int i = 0; i < 10; i++){
            System.out.println("-----------------------------------------------------------------------");
            Thread.sleep(2 * 1000);
            //通过代理对象调用服务提供者的方法(服务)
            String res = service.hello("你好 dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
        System.exit(0);
    }
}
