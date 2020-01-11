package pers.weisg.base.springboot_demo.nio.zerocopy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author weisg
 * @description 服务器
 * @date 2019/12/5 0005
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7002);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(inetSocketAddress);
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(8*1024*1024);

        //FileOutputStream fileOutputStream = new FileOutputStream("heidisql2.rar");
        //FileOutputStream fileOutputStream = new FileOutputStream("SwitchHosts2.zip");
        //FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            //fileOutputStreamChannel.transferFrom(socketChannel,0, socketChannel);
            int readCount = 0;
            while(-1 != readCount){
                try {
                    readCount = socketChannel.read(byteBuffer);
                    //byteBuffer.flip();//切换读写
                } catch (IOException e) {
                    //e.printStackTrace();
                    break;
                }
                //使用transferForm完成拷贝

            }
        }
    }
}
