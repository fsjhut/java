package com.socket;

import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author SunHang
 * @className: TcpTest
 * @description:
 * @createTime 2021/4/12 17:07
 */
public class InetAddrUrlTest {
    public static void main(String[] args) {
        getAllAddress();
        getCompIp();
        getWeatherData(101010100);
    }

    private static void getWeatherData(int cityId) {

        String path = "http://t.weather.itboy.net/api/weather/city/" + cityId;
        BufferedReader reader = null;
        try {
            URL url = new URL(path);
            // 读取网络数据------> 字节输入流
            URLConnection connection = url.openConnection();
            connection.connect();
            // 网络数据一般只有一行，使用高效字符流
            reader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream()));
            // 只用读取一行的数据
            String info = reader.readLine();
            System.out.println(info);
            // 如何将字符串数据转为一个Map集合呢？
            stringToObject(info);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void stringToObject(String info) {
        // 利用fast json.jar的工具包
        Map<String,Object> result = (Map<String,Object>)JSONArray.parse(info);
        System.out.println(result);
        Map<String,Object> result2 = JSONArray.parseObject(info, Map.class);
    }

    private static void getUrl() throws IOException {
        String path = "http://www.baidu.com";
        try {
            URL url = new URL(path);
            // 获取协议
            System.out.println(url.getProtocol());
            // 端口 -1
            System.out.println(url.getPort());
            // 协议端口
            System.out.println(url.getDefaultPort());

            System.out.println(url.getHost());
            System.out.println(url.getAuthority());
            // 路径?后面的内容
            System.out.println(url.getQuery());
            System.out.println("------------");
            System.out.println(url.getFile());
            // #后面的标记，锚记
            System.out.println(url.getRef());
            // 获取的是一个对象
            System.out.println(url.getContent());
            // 获取的是对象流
            System.out.println(url.openStream());
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            // 也是一个对象 --->输入流的对象，可以作为InputStreamReader构造器的参数
            System.out.println(urlConnection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void getCompIp() {
        // 获得计算机的ip地址(整形)
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
            String hostAddress = localHost.getHostAddress();
            System.out.println(localHost.getHostName());
            String[] split = hostAddress.split("\\.");
            int[] intArr = new int[4];
            for (int i = 0; i < split.length; i++) {
                intArr[i] = Integer.parseInt(split[i]);
            }
            System.out.println(Arrays.toString(intArr));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的ip地址信息
     */
    public static void getAllAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface anInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = anInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (inetAddress instanceof Inet6Address) {
                        System.out.println("Ipv6: " + inetAddress);

                    } else {
                        System.out.println("Ipv4:" + inetAddress);
                    }
                }
                System.out.println("-----------------------");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
