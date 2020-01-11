package pers.weisg.base.springboot_demo.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/4 0004
 */
public class GroupChatClient {

    //定义相关的属性
    private final String HOST = "127.0.0.1";
    private final int PORT = 6668;
    private Selector selector;
    private SocketChannel socketChannel;
    //private SocketChannel socketChannel;
    private String username;

    //构造器, 完成初始化工作
    public GroupChatClient() throws IOException{
        selector = Selector.open();
        //连接服务器
        //socketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        //socketChannel.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //将channel 注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + " is ok...");
    }

    /**
     * 读取从服务器端回复的消息
     */
    public void readInfo() {
        try {
            int readChannels = selector.select();
            if(readChannels > 0){//有可以用的通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isReadable()){
                        //得到相关的通道
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        //得到一个Buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //读取
                        socketChannel.read(byteBuffer);
                        //把读到的缓冲区的数据转成字符串
                        String message = new String(byteBuffer.array());
                        System.out.println(message.trim());
                    }
                }
                iterator.remove();//删除当前的selectionKey, 防止重复操作
            }else{
                System.out.println("没有可以用的通道...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 向服务器发送消息
     * @param info
     */
    public void sendInfo(String info) {
        info = username + " 说：" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        //启动我们客户端
        GroupChatClient chatClient = new GroupChatClient();
        //启动一个线程, 每个3秒，读取从服务器发送数据
        new Thread(() -> {
               while(true){
                   chatClient.readInfo();
                   try { TimeUnit.SECONDS.sleep(3);} catch(InterruptedException e){ e.printStackTrace();}
               }
        },"AAA").start();

        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            chatClient.sendInfo(str);
        }
    }
}
