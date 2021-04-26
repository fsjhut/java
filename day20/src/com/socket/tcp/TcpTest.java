package com.socket.tcp;
/**
 * @className: TcpTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/12 19:03
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 两个进程(应用程序)之间可以通过一个双向的网络通信连接
 * 实现数据交换，这种通信链路的端点被称为“套接字”(socket)
 */
public class TcpTest {
    public static void main(String[] args) {
        try{
            //1. 创建一个服务端程序，监听某一个端口
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("----------服务器开启--------------");
            //2. 等待客户端的连接
            while (true){
                // 阻塞的方式进行等待
                Socket client = serverSocket.accept();
                System.out.println("成功连接的客户端："+client.getInetAddress());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
