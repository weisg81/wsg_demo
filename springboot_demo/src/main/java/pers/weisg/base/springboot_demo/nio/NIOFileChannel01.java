package pers.weisg.base.springboot_demo.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/2 0002
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String str = "hello 小韦";
        //创建一个输出流 -》channel
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\tmp\\file01.txt");

        //通过fileOutputStream 获取对应的FileChannel
        // 这个FileChannel 真实类型是：FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将Str放入byteBuffer
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        //将byteBuffer 数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
