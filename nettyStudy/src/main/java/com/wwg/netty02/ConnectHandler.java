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

    /*
    Channel channel = ...;
    //Does not block
    ChannelFuture future = channel.connect(
            //异步地连接到远程节点
            new InetSocketAddress("192.168.0.1",25));
    //注册一个ChannelFutureListener，以便在操作完成时获得通知
    future.addListener(new ChannelFutureListener){
        public void operationComplete(ChannelFuture future){
            //检查操作的状态
            if(future.isSuccess()){
                //如果操作是成功的，则创建一个ByteBuf以持有数据
                ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                //将数据异步地发送到远程节点。返回一个ChannelFuture
                ChannelFuture wf = future.channel().writeAndFlush(buffer);
                ....
            } else{
                //如果发生错误，则访问描述原因的Throwable
                Throwable cause = future.cause();
                cause.printStackTrace();
            }
        }
    }
    */
}
