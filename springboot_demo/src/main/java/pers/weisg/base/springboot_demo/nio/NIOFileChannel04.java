package pers.weisg.base.springboot_demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/3 0003
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\tmp\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\tmp\\a2.jpg");

        //获取相关的channel
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        outputStreamChannel.transferFrom(inputStreamChannel,0, inputStreamChannel.size());

        //关闭相关的通道和流
        inputStreamChannel.close();
        outputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
