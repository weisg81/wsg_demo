package pers.weisg.base.springboot_demo.netty.codec2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import pers.weisg.base.springboot_demo.netty.codec.StudentPOJO;

/**
 * @author weisg
 * @description 说明
 * 1. 我们自定义一个Handler 需要继续netty 规定好的某个HandlerAdapter(规范)
 * 2. 这时我们自定义一个Handler , 才能称为一个handler
 * @date 2019/12/6 0006
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    /**
     * 读取数据实际(这里我们可以读取客户端发送的消息)
     * @param ctx 上下文对象, 含有 管道pipeline , 通道channel, 地址
     * @param msg 就是客户端发送的数据 默认Object
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        //读取从客户端发送的 MyDataInfo.MyMessage
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if(dataType ==  MyDataInfo.MyMessage.DataType.StudentType){
            MyDataInfo.Student student = msg.getStudent();
            System.out.println("学生id=" + student.getId() + " 学生名字=" + student.getName());
        }else if(dataType == MyDataInfo.MyMessage.DataType.WorkerType){
            MyDataInfo.Worker worker = msg.getWorker();
            System.out.println("工人的名字=" + worker.getName() + " 年龄=" + worker.getAge());
        }else{
            System.out.println("传输的类型不正确");
        }
    }
    /*
     * 读取数据实际(这里我们可以读取客户端发送的消息)
     * @param ctx 上下文对象, 含有 管道pipeline , 通道channel, 地址
     * //@param msg 就是客户端发送的数据 默认Object
     * @throws Exception
     */
    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);

        Channel channel = ctx.channel();
        System.out.println("服务器读取线程 " + Thread.currentThread().getName() + " channle =" + channel);
        System.out.println("server ctx =" + ctx);
        System.out.println("看看channel 和 pipeline的关系");
        ChannelPipeline pipeline = ctx.pipeline();//本质是一个双向链接, 出站入站
        //将 msg 转成一个 ByteBuf
        //ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + channel.remoteAddress());

    }*/

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
