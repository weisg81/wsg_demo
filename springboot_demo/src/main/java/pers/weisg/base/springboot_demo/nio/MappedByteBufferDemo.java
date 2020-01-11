package pers.weisg.base.springboot_demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weisg
 * @description MappedByteBuffer 可以让文件直接在内存（堆外内存）中修改，操作系统不需要拷贝一次
 * @date 2019/12/3 0003
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        //MappedByteBuffer
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        //获取对应的通道
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        /**
         * READ_WRITE 读写模式
         * 参数2：0  可以直接修改的起始位置
         * 参数3：5  是映射到内存的大小，即将1.txt的多少个字节映射到内存 可以直接修改的范围是0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = accessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte)'H');
        mappedByteBuffer.put(3, (byte)'9');
        mappedByteBuffer.put(5, (byte)'Y');//java.lang.IndexOutOfBoundsException

        randomAccessFile.close();
    }
}
