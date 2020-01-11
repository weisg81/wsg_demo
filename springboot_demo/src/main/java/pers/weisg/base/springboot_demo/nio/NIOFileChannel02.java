package pers.weisg.base.springboot_demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weisg
 * @description java输出流
 * @date 2019/12/2 0002
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception{
        //文件流->channel->buffer
        File file = new File("F:\\tmp\\file01.txt");
        FileInputStream inputStream = new FileInputStream(file);

        //通过fileOutputStream 获取对应的FileChannel
        // 这个FileChannel 真实类型是：FileChannelImpl
        FileChannel fileChannel = inputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //将通道的数据读入到Buffer中，read
        fileChannel.read(byteBuffer);

        //
        System.out.println(new String(byteBuffer.array()));

        //关闭流
        inputStream.close();
    }
}
