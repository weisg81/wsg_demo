package pers.weisg.base.springboot_demo.netty.simple;

import io.netty.util.NettyRuntime;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/6 0006
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
