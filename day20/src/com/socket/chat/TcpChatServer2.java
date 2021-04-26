package com.socket.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SunHang
 * @className: TcpChatServer2
 * @description:
 * @createTime 2021/4/13 16:48
 */
public class TcpChatServer2 {
    public static final String serverName = "server:";

    public static void main(String[] args) throws IOException {

        //Exception in thread "main" java.net.BindException: Address already in use: JVM_Bind
        //端口号被占用了  服务端程序启动2次
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("-------------聊天开始--------------");
        while (true) {
            Socket client = serverSocket.accept();
            ReadWriteMsg readWriteMsg = new ReadWriteMsg(client);
            new Thread(() -> {
                while (true) {
                    String readMsg = readWriteMsg.readMsg();
                    broadcast(readWriteMsg,readMsg);
                }
            }, serverName).start();
            broadcast(readWriteMsg);
        }
    }

    public static void broadcast(ReadWriteMsg readWriteMsg){
        new Thread(() -> {
            while (true) {
                readWriteMsg.writeMsg();
            }
        }, serverName).start();
    }
    public static void broadcast(ReadWriteMsg readWriteMsg,String str){
        new Thread(() -> {
            while (true) {
                readWriteMsg.writeMsg(str);
            }
        }, serverName).start();
    }
}
