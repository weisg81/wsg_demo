package pers.weisg.base.springboot_demo.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author weisg
 * @description 说明
 *  1. SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
 *  2. HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject
 * @date 2019/12/9 0009
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * channelRead0 读取客户端数据
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        System.out.println("对应的channel="+channelHandlerContext.channel()+", pipeline="+channelHandlerContext.pipeline()+
        ", 通过pipeline获取channel="+channelHandlerContext.pipeline().channel());
        System.out.println("当前ctx的handler="+channelHandlerContext.channel());
        //判断 msg 是不是 httprequest请求
        if(httpObject instanceof HttpRequest){
            System.out.println("channelHandlerContext 类型："+channelHandlerContext.getClass());
            System.out.println("pipeline hashcode:"+channelHandlerContext.pipeline().hashCode()+", TestHttpServerHandler hash:"+this.hashCode());
            System.out.println("httpObject 类型："+httpObject.getClass());
            System.out.println("客户端地址："+channelHandlerContext.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest)httpObject;

            //获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            //回复信息给浏览器 [http协议]
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            //构造一个http的相应，即 httpresponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
            //response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            //将构建好 response返回
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
