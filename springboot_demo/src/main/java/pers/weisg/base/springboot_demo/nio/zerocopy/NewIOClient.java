package pers.weisg.base.springboot_demo.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/5 0005
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7002));
        //String fileName = "heidisql.rar";
        String fileName = "SwitchHosts.zip";//8,583,773 字节
        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();

        int maxTransferSize = 8 * 1024 * 1024;

        //在linux下一个transferTo 方法就可以完成传输
        //在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要主要
        //传输时的位置 =》 课后思考...
        //transferTo 底层使用到零拷贝
        long transferToltalCount = 0;
        /*while(fileChannel.position() < fileChannel.size()){
            long transferCount = 0;
            transferCount = fileChannel.transferTo(fileChannel.position(), maxTransferSize, socketChannel);
            *//*if(fileSurplusSize > maxTransferSize){

            }else{
                transferCount = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
            }*//*
            //position = fileChannel.position();
            fileChannel.position(fileChannel.position()+transferCount);
            transferToltalCount += transferCount;
        }*/
        long transferCount = 0;
        /*transferCount = fileChannel.transferTo(fileChannel.position(), maxTransferSize, socketChannel);
            *//*if(fileSurplusSize > maxTransferSize){

            }else{
                transferCount = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
            }*//*
        //position = fileChannel.position();
        fileChannel.position(fileChannel.position()+transferCount);
        transferToltalCount += transferCount;*/
        transferCount = fileChannel.transferTo(fileChannel.position(), fileChannel.size(), socketChannel);
        transferToltalCount += transferCount;
        //long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        //System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
        System.out.println("发送的总的字节数 =" + transferToltalCount + " 耗时:" + (System.currentTimeMillis() - startTime));
        //关闭
        fileChannel.close();
    }
}
