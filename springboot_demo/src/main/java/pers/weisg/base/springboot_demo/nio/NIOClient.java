package pers.weisg.base.springboot_demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/3 0003
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端的ip 和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
        //连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作..");
            }
        }
        //如果连接成功，就发送服务器
        String str = "hello weisg";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据，实际是将数据写入channel
        socketChannel.write(byteBuffer);
        System.in.read();

    }
}
