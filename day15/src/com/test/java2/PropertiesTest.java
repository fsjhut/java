package com.test.java2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @className: PropertiesTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 19:50
 */
public class PropertiesTest {

    // Properties : 常用于处理配置文件
    // key和value都是String类型
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            Properties pros = new Properties();
            // 方式一
//            fis = new FileInputStream("day15\\src\\jdbc.properties");
//            pros.load(fis);
            // 方式二
            ClassLoader classLoader = PropertiesTest.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("jdbc.properties");
            pros.load(is);
            String name = pros.getProperty("name");
            String password = pros.getProperty("password");
            System.out.println(name + password);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
