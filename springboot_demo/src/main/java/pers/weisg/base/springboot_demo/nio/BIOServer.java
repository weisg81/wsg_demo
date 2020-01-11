package pers.weisg.base.springboot_demo.nio;

import pers.weisg.base.springboot_demo.common.MyThreadPoolUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/2 0002
 */
public class BIOServer {

    public static void main(String[] args) throws Exception{
        //创建一个线程池
        ExecutorService threadService = MyThreadPoolUtils.getExecutorService();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动了。。。");
        System.out.println("等待连接。。。");
        while(true){

            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            threadService.execute(() -> {
                //和客户端进行通讯
                handle(socket);
            });
        }
    }

    //编写一个handle方法，和客户端进行通讯
    public static void handle(Socket socket){
        try {
            System.out.println("name="+Thread.currentThread().getName()+"\t id="+Thread.currentThread().getId());
            byte[] bytes = new byte[1024];
            //通过socket获取一个图稿流
            InputStream inputStream = socket.getInputStream();
            System.out.println("wati read.....");
            //循环读取客户端发送的数据
            while(true){
                System.out.println("name="+Thread.currentThread().getName()+"\t id="+Thread.currentThread().getId());
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));//输出客户端发送的数据
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
