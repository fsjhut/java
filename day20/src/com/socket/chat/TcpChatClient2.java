package com.socket.chat;

import java.io.IOException;
import java.net.Socket;

/**qwer
 * @className: TcpChatClient2
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 16:48
 */
public class TcpChatClient2 {
    public static void main(String[] args) throws IOException {

        final String clientName = "client:";
        Socket socket = new Socket("localhost", 8888);

        ReadWriteMsg readWriteMsg = new ReadWriteMsg(socket);
        new Thread(() -> {
            while (true) {
                readWriteMsg.readMsg();
            }
        }, clientName).start();
        new Thread(() -> {
            while (true) {
                readWriteMsg.writeMsg();
            }
        }, clientName).start();
    }
}

