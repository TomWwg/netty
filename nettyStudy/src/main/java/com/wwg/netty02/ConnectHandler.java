package com.wwg.netty02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by wwg on 2018/5/25.
 * @author wwg
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
    }
}
