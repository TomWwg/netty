package com.wwg.netty02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 *
 * @author iel201704
 * @date 2018/5/30
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.out.println("Usage：" + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        //设置端口值（如果端口从参数的格式不正确，则抛出一个NumberFormatException）
        int port = Integer.parseInt(args[0]);
        //调用服务器的start()方法
        new EchoServer(port).start();
    }

    private void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            //创建ServerBootStrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    //使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServer到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception{
                    //EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                    channel.pipeline().addLast(serverHandler);
                }
            });
            //异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = b.bind().sync();
            //获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

}
