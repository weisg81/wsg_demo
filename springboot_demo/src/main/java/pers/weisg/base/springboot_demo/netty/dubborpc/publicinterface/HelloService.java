package pers.weisg.base.springboot_demo.netty.dubborpc.publicinterface;

/**
 * @author weisg
 * @description 服务提供方和 服务消费方都需要
 * @date 2019/12/16 0016
 */
public interface HelloService {
    String hello(String mes);
}
