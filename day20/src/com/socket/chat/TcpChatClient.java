package com.socket.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: TcpChatClient
 * @description:
 * @createTime 2021/4/13 16:22
 */
public class TcpChatClient {
    public static void main(String[] args) throws IOException {
        final String clientName = "client:";
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 9999);
        while (true) {
            // 接受服务器发送的信息
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            System.out.println(dataInput.readUTF());
            // 发送信息
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
            System.out.print(clientName);
            String s = scanner.nextLine();
            dataOutput.writeUTF(clientName + s);
        }
    }
}
