package com.wwg.netty03;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 未使用Netty的阻塞网络编程
 * 阻塞传输
 * @author wwg
 * @date 2018/6/4
 */
public class PlainOioServer {
    public void serve(int port) throws IOException{
        //将服务器绑定到指定窗口
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;){
                //接收连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                //创建一个新的线程来处理该连接
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream outputStream;
                        try {
                            outputStream = clientSocket.getOutputStream();
                            //将消息写给已连接的客户端
                            outputStream.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                            outputStream.flush();
                            //关闭连接
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //启动线程
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
