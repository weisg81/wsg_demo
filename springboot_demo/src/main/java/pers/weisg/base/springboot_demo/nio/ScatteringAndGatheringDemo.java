package pers.weisg.base.springboot_demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author weisg
 * @description Scattering 将数据写入到Buffer时，可以采用buffer数组，依次写入
 *  Gathering 从buffer读取数据时，可以采用buffer数组，依次读
 * @date 2019/12/3 0003
 */
public class ScatteringAndGatheringDemo {
    public static void main(String[] args) throws Exception{

        //使用 ServerSocketChannel 和 SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接socket
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;//假定从客户端接收8个字节

        while(true){
            int byteRead = 0;
            while(byteRead < messageLength){
                long read = socketChannel.read(byteBuffers);
                byteRead += read;//累计读到的字节数
                System.out.println("byteRead="+byteRead);
                //使用流打印，看看当前的这buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "postion="+byteBuffer.position()+",limit="+byteBuffer.limit())
                        .forEach(System.out::println);
            }

            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            //将数据读出显示到客户端
            long byteWrite  = 0;
            while(byteWrite < messageLength){
                long write = socketChannel.write(byteBuffers);
                byteRead += write;//累计读到的字节数
            }
            //将所有的buffer复位，clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead="+byteRead+",byteWrite"+byteWrite+",messageLength="+messageLength);
        }
    }
}
