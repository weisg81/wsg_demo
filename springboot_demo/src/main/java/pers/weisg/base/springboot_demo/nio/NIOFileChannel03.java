package pers.weisg.base.springboot_demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/3 0003
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("1.txt");
        FileChannel fileChannel = inputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            //复位操作，不能忘记了，很重要，很重要，很重要
            byteBuffer.clear();//清空Buffer
            int read = fileChannel.read(byteBuffer);
            System.out.println("read = "+read);
            if(read != -1){
                byteBuffer.flip();//切换读写
                //将buffer中的数据写入到输出channel中 --》2.txt
                fileOutputStreamChannel.write(byteBuffer);
            }else {
                //读取完成
                break;
            }
        }
        //关闭相关的流
        inputStream.close();
        fileOutputStream.close();
    }
}
