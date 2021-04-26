package com.socket.tcp;

import java.net.Socket;

/**
 * @className: TcpClientTest
 * @description: 创建一个客户端，连接服务器
 * @author SunHang
 * @createTime 2021/4/12 19:08
 */
public class TcpClientTest {
    public static void main(String[] args) {
        try{
            // 主动发起连接，特定的服务端程序
            // 需要传入ip地址和端口号
            Socket socket = new Socket("192.168.14.246", 8888);
            System.out.println("server: "+socket);
        }catch (Exception e){

        }
    }
}
