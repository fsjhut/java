package com.socket.transfile;

import javax.naming.Name;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;

/**
 * @className: TcpServer
 * @description:
 * @author SunHang
 * @createTime 2021/4/12 19:14
 */

/**
 * 首先接受用户传过来的文件名 判断是文件还是目录
 * ？？怎么在客户的传输流中确定是文件还是目录？
 * 在upload路径下查看用户的文件夹是否存在，
 * 若不存在，则建立一个文件夹
 * 将编号+文件名 + 文件后缀 一起保存到文件中
 */
public class FileTcpServer {
    /**
     * LongAdder: 多线程竞争时可以保证id的唯一性，
     * 常用于计数统计的领域
     * private static LongAdder adder = new LongAdder();
     * static {
     * // 通过静态代码块给类变量赋值
     * adder.add(1000);
     * }
     * public static int getId(){
     * // adder 的自增操作
     * adder.increment();
     * // 返回的是int类型数据
     * return adder.intValue();
     * }
     */
    public static final String targetFilePath = "E:\\备份";
    public static String rootPath = "";
    public static String userRootPath = "";
    public static String basePath = "";
    public static String baseRootPath = "";

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {
        ServerSocket imageSocket = null;
        try {
            imageSocket = new ServerSocket(8888);
            System.out.println("------图片服务器开启------");
            while (true) {
                // 等待客户端的连接
                Socket client = imageSocket.accept();
                // 连接成功则建立用户的文件夹
                userRootPath = client.getLocalAddress().getHostName();
                File file = new File(targetFilePath + "\\" + userRootPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                // 保存客户端传入的图片的位置
//                String targetFilePath =  "";

                // 第一次接受时，接受到的一定是data流内容
                DataInput dataInput = new DataInputStream(client.getInputStream());
                String readInfo = dataInput.readUTF();
                String destFilePath = "";
                basePath = targetFilePath + "\\" + userRootPath;
                // 如果本次传输的就是一个文件任务
                if (readInfo.charAt(0) == '&') {
                    String fileName = readInfo.substring(1, readInfo.length());
                    destFilePath = basePath + "\\" + fileName;
                    acceptFile(client,destFilePath);
                    continue;
                }

//                System.out.println(readInfo);
                // @ 标记一个初始的文件夹
                if (readInfo.charAt(0) == '@') {
                    rootPath = readInfo.substring(1, readInfo.length());
                    baseRootPath = basePath + "\\" + rootPath;
                    File file2 = new File(baseRootPath);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                }
                // # 是文件目录则建立目录
                if (readInfo.charAt(0) == '#') {
                    String name = readInfo.substring(1, readInfo.length());
                    destFilePath = baseRootPath + "\\" + name;
                    File file3 = new File(destFilePath);
                    if (!file3.exists()) {
                        file3.mkdir();
                    }
                }
                // * 标记的是文件
                if (readInfo.charAt(0) == '*') {
                    String filePath = readInfo.substring(1, readInfo.length());
                    destFilePath = baseRootPath + "\\" + filePath;
                    // 接受文件数据
                    acceptFile(client, destFilePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                Objects.requireNonNull(imageSocket);
                imageSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void acceptFile(Socket client, String destFilePath) {
        try {
            // 读取客户端读取的数据
            InputStream inputStream = client.getInputStream();
            // 将读取到客户端传来的数据写入到目标文件中
            // 输出流对象
            FileOutputStream outputStream = new FileOutputStream(destFilePath);
            // 指定每次写入的字节数组大小
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
//            // 服务器告诉客户端已经上传成功
//            // 建立一个输出流的Data输出对象
//            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
//            dataOutputStream.writeUTF("图片上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
