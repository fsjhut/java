package com.IOstream;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @className: IOStreamTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/25 13:27
 */
public class IOStreamTest {
    @Test
    public void test1() throws InterruptedException {
//        ThreadLocal();
        System.out.println(Thread.currentThread());
//        Objects.requireNonNull()
        Thread.sleep(1000);
        TimeUnit.MILLISECONDS.sleep(100);
//        Callable();
//        FutureTask;
//        ThreadPoolExecutor;
//        Executors;
//        LongAdder;
//        String.class.wait();
//        synchronized ();
//        client;
//        InetAddress

    }
    @Test
    public void test2() throws IOException {
        String path = "http://t.weather.itboy.net/api/weather/city/101010100";

        URL url = new URL(path);
        System.out.println(url.getProtocol());
        System.out.println(url.getDefaultPort());
        System.out.println(url.getHost());
        System.out.println(url.getAuthority());
        System.out.println(url.getQuery());
        System.out.println(url.getFile());
        System.out.println(url.getRef());
        System.out.println(url.getContent());
        System.out.println("-------------");
        System.out.println(url.openStream());
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
//        inputStream.
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String info = reader.readLine();
        System.out.println(info);
//        ServerSocket
//        Number
    }
}
