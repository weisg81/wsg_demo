package pers.weisg.base.springboot_demo.netty.dubborpc.provider;


import pers.weisg.base.springboot_demo.netty.dubborpc.netty.NettyServer;
import pers.weisg.base.springboot_demo.netty.dubborpc.netty.NettyServerHandler;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/16 0016
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
