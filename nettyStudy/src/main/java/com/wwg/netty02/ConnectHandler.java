package com.wwg.netty02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 被回调触发的ChannelHandler
 * Created by wwg on 2018/5/25.
 * @author wwg
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }

    /**
     * 异步地建立连接
     */
//    Channel channel = ...;
//    ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1",25));
}
