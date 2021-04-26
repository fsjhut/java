package com.socket.chat;

import com.sun.istack.internal.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: ReadWriteMsg
 * @description:
 * @createTime 2021/4/13 16:22
 */
public class ReadWriteMsg {
    private static Scanner scanner;
    @NotNull
    private Socket socket;

    static {
        scanner = new Scanner(System.in);
    }

    public ReadWriteMsg(Socket socket) {
        this.socket = socket;
    }

    public String readMsg() {
        try {
            DataInputStream dataInput = new DataInputStream(socket.getInputStream());
            String strInfo = dataInput.readUTF();
            System.out.println(strInfo);
            return strInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeMsg() {
        try {
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
            String threadName = Thread.currentThread().getName();
            System.out.print(threadName);
            String msg = scanner.nextLine();
            if ("bye".equals(msg)) {
                System.exit(-1);
            }
            dataOutput.writeUTF(threadName + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeMsg(String string) {
        try {
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
            String threadName = Thread.currentThread().getName();
            dataOutput.writeUTF(threadName + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
