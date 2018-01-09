package com.wwg.netty01;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author wwg
 * @date 2018/1/8
 */
public class Client {

    private  int port;
    private  String address;

    public Client(int port,String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        /**
         * EventLoop目的是为Channel处理IO操作，
         * 一个EventLoop可以为多个Channel服务,
         * EventLoopGroup会包含多个EventLoop。
         */
        EventLoopGroup group = new NioEventLoopGroup();

        //一个Netty应用通常由一个Bootstrap开始，它主要作用是配置整个Netty程序，串联起各个组件。
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        try {
            Channel channel = bootstrap.connect(address,port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String msg = reader.readLine();
                if(msg == null){
                    continue;
                }
                channel.writeAndFlush(msg + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        Client client = new Client(7788,"127.0.0.1");
        client.start();
    }
}
