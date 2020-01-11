package pers.weisg.base.springboot_demo.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author weisg
 * @description TODO
 * @date 2019/12/9 0009
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器

        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        /**
         * HttpServerCodec 说明
         *  1. HttpServerCodec 是netty 提供的处理http的 编-解码器
         *  2. 增加一个自定义的handler
         */
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        pipeline.addLast("MyTestHttpServerHandler",new TestHttpServerHandler());
        System.out.println("ok.............");
    }
}
