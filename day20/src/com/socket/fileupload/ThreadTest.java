package com.socket.fileupload;

import java.io.*;
import java.net.Socket;

/**
 * @className: ThreadTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 12:38
 */
public class ThreadTest implements Runnable{
    public  String fileInitialPath;
    public  File file;

    public ThreadTest(String fileInitialPath, File file) {
        this.fileInitialPath = fileInitialPath;
        this.file = file;
    }

    private  void fileUpload() {
//        String fileName = file.getName();
        System.out.println(Thread.currentThread().getName());
        String filePath = file.getAbsolutePath();
        // 文件的绝对路径要进行处理，绝对路径减去最初的filePath
        String truePath = updatePath(filePath);
        try {
            Socket socket = new Socket("192.168.14.246", 8888);
            // 先传输，文件名和文件的路径
            DataOutputStream firstStream = new
                    DataOutputStream(socket.getOutputStream());
            firstStream.writeUTF("*" + truePath);
//            socket.shutdownOutput();

            // 读取文件的内容，传递给服务器
            byte[] bytes = new byte[1024];
            int len;
//            System.out.println(file.exists());
            FileInputStream inputStream = new FileInputStream(file);
//            FileInputStream inputStream = new FileInputStream(file);
            OutputStream contentStream = socket.getOutputStream();
            while ((len = inputStream.read(bytes)) != -1) {
                contentStream.write(bytes, 0, len);
            }
            // 关闭输出流
            socket.shutdownOutput();
            // 关闭套接字
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  String updatePath(String filePath) {
        // filePath - fileInitialPath
        String truePath = filePath.replace(fileInitialPath, "");
        String truePath2;
        if("".equals(truePath)){
            truePath2 = "";
        }else{
            truePath2 = truePath.substring(1,truePath.length());
        }
        return truePath2;
    }

    @Override
    public void run() {
        fileUpload();
    }
}
