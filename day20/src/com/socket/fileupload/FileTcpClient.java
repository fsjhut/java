package com.socket.fileupload;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. 可以传输文件或者目录
 * 2. 对文件或文件的大小进行记录
 * 3. 使用多线程
 * 4. 在客户端显示文件传输的进度
 * 5. 传输完成后 计算总文件的大小，
 * 与客户端的比对是否一致
 * 6. 比对成功，则提示用户上传已成功
 *
 * @author SunHang
 * @className: TcpClient
 * @description: 客户端上传到服务器
 * @createTime 2021/4/12 19:14
 */
public class FileTcpClient {
    public static Scanner scanner = new Scanner(System.in);
    public static String fileInitialPath;
    public static ExecutorService threadPool = Executors.newCachedThreadPool();


    public static void main(String[] args) {
        // 定义一个上传的路径
        System.out.println("=======欢迎使用文件上传系统!========");
        System.out.println("请输入你要上传的文件目录：");
        fileInitialPath = scanner.nextLine();
        File file = new File(fileInitialPath);
        if (!file.exists()) {
            System.out.println("输入的文件名有误！");
            return;
        }
        if (file.isDirectory()) {
            // 如果是一个文件目录，则先向服务器传入文件名
            uploadPath(file);
            // 调用文件夹上传的方法
            fileHandle(file);
        } else {
            fileUpload2(file);
        }
    }

    public static void fileHandle(File file) {
        if (!file.exists()) {
            return;
        }
        // 调用递归的方法进行文件的传输
        File[] child = file.listFiles();
        Objects.requireNonNull(child);
        for (File file1 : child) {
            if (file1.isDirectory()) {
                // 先上传路径
                uploadPath(file1);
                // 递归调用
                fileHandle(file1);
            } else {
                // 执行文件上传的任务
//                fileUpload(file1);
//                Thread thread = new Thread(new ThreadTest(fileInitialPath, file1));
//                thread.start();
                threadPool.submit(new ThreadTest(fileInitialPath, file1),file1.getName());
//                System.out.println();
            }
        }
    }

    //E:\个人笔记\项目V1.0\SharedBike.java
    private static void uploadPath(File file1) {
        // 如果是目录，上传到目录路径给服务器，
        // 服务器接受到目录后，创建一个文件夹
        String absPath = file1.getAbsolutePath();
        String truePath = updatePath(absPath);
        try {
            Socket socket = new Socket("192.168.14.246", 8888);
            DataOutputStream firstStream = new
                    DataOutputStream(socket.getOutputStream());
            if ("".equals(truePath)) {
                // 第一次传输，应该传输根文件名
                // E:\资料\课件\第一阶段
//                String[] split = fileInitialPath.split("\\\\");
//                String rootPath = split[split.length - 1];
                firstStream.writeUTF("@" + file1.getName());
//                System.out.println(file1.getName());
                socket.shutdownOutput();
                socket.close();
            } else {
                firstStream.writeUTF("#" + truePath);
                // 关闭输出流
                socket.shutdownOutput();
                // 关闭套接字
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void fileUpload(File file) {
////        String fileName = file.getName();
//        String filePath = file.getAbsolutePath();
//        // 文件的绝对路径要进行处理，绝对路径减去最初的filePath
//        String truePath = updatePath(filePath);
//        try {
//            Socket socket = new Socket("192.168.14.246", 8888);
//            // 先传输，文件名和文件的路径
//            DataOutputStream firstStream = new
//                    DataOutputStream(socket.getOutputStream());
//            firstStream.writeUTF("*" + truePath);
////            socket.shutdownOutput();
//
//            // 读取文件的内容，传递给服务器
//            byte[] bytes = new byte[1024];
//            int len;
////            System.out.println(file.exists());
//            FileInputStream inputStream = new FileInputStream(file);
////            FileInputStream inputStream = new FileInputStream(file);
//            OutputStream contentStream = socket.getOutputStream();
//            while ((len = inputStream.read(bytes)) != -1) {
//                contentStream.write(bytes, 0, len);
//            }
//            // 关闭输出流
//            socket.shutdownOutput();
//            // 关闭套接字
//            socket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static String updatePath(String filePath) {
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

    private static void fileUpload2(File file) {
        String fileName = file.getName();
        try {
            Socket socket = new Socket("192.168.14.246", 8888);
            // 先传输，文件名和文件的路径
            DataOutputStream firstStream = new
                    DataOutputStream(socket.getOutputStream());
            firstStream.writeUTF("&"+fileName);
//            socket.shutdownOutput();
            // 读取文件的内容，传递给服务器
            byte[] bytes = new byte[1024];
            int len;
//            FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            FileInputStream inputStream = new FileInputStream(file);
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
}
