package pers.weisg.base.springboot_demo.nio;

import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/2 0002
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //说明buffer的使用
        //创建一个Buffer 大小为5，可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }

        //读取数据 将buffer 转换，读写切换
        intBuffer.flip();
        intBuffer.position(2);
        intBuffer.limit(5);
        SocketChannel socketChannel = null;
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
