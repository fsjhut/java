package com.socket.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @className: TcpChatServer
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 16:21
 */
public class TcpChatServer {
    public static void main(String[] args) throws IOException {
        final String serverName = "Server:";
        Scanner scanner = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("---------聊天开始-------------");
        while (true) {
            Socket client = serverSocket.accept();
            // 读写数据
            while (true) {
                // 服务器写给客户端
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                System.out.print(serverName);
                String str = scanner.nextLine();
                output.writeUTF(serverName + str);
                // 服务器读取客户端的数据
                DataInputStream dataInput = new DataInputStream(client.getInputStream());
                System.out.println(dataInput.readUTF());
            }
        }
    }
}
