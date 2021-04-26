package com.oop;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

import static sun.misc.Version.println;

/**
 * @author SunHang
 * @className: OOPTest
 * @description: clone是值传递机制，即若属性是引用类型，则直接将内存地址值copy一次
 * 若修改clone对象引用类型中的值，则原来的值也将被修改
 * 若想要将对象中的引用属性也clone，则必须对该引用属性再一次调用clone
 * oop = (StudentClone)super.clone();
 * oop.hobby = oop.hobby.clone(); // hobby是引用类型，因此需要对hobby进行clone
 * 相当于clone时，只复制一层地址值，若属性有多层的引用，则需要递归的进行clone
 * @createTime 2021/4/24 9:40
 */
public class StudentCloneTest {
    @Test
    public void test1() {
        String[] hobby = {"play", "read", "study"};
        StudentClone oop = new StudentClone(1, "sun", hobby);
        System.out.println(oop);
        StudentClone oop2 = oop.clone();
        System.out.println(oop2);
        hobby[1] = "play game";
        oop.setName("hang");
        System.out.println(oop);
        System.out.println(oop2);
        SoftReference<StudentClone> softReference = new SoftReference<>(oop);
        System.out.println("---------------------------");
        System.out.println(softReference.get());
//        oop = null;
//        System.out.println(softReference.get());
//        System.out.println(oop);
        System.out.println(oop.getClass());
    }

    @Test
    public void test2() {
        Integer num1 = 10;
        Integer num2 = 10;
        System.out.println(num1);
        System.out.println(num1 == num2);
    }

    @Test
    public void test3() {
        // 字符串类常用的方法
        // StringBuild 常用，线程不安全，效率高
        // StringBuffer 不常用，线程安全，效率低
        // String 内部是通过一个private final char[]来维护的，因此值不可变
        // String 中 utf8是三个字节，GBK：2个字节
        // String 中有三种构造器，1. byte[]:字节数组
        //                       2. char[]:字符数组  3. 字符串
        // 字节(8个bit位 可表示-128-127)，
        // 字符：长度不定，与编码格式有关，utf8汉字3个字节，英文1个字节
        String s1 = "Str";
        String s2 = "Str";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1 == s2);  // true
        byte[] bytes = "孙航".getBytes();
        char[] chars = new char[6];
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
            char ch = (char) bytes[i];
            chars[i] = ch;
        }
        System.out.println(chars);
        byte[] newByte = new byte[6];
        for (int i = 0; i < newByte.length; i++) {
            newByte[i] = (byte) chars[i];
        }
        String sh = new String(newByte);
        System.out.println(sh);
    }

    @Test
    public void test4() {
        char[] chars = "孙航".toCharArray();
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            System.out.println((int) chars[i]);
            str = str + (int) chars[i];
        }
        System.out.println(chars);
        System.out.println(str);
//        String substring = str.substring(0, 2);
//        System.out.println(substring);
        char[] chars2 = new char[10];
        str.getChars(0, str.length(), chars2, 0);
        System.out.println(chars2);
        StringBuilder[] builders = new StringBuilder[5];
        for (int i = 0; i < chars2.length; i = i + 2) {
            builders[i / 2] = new StringBuilder();
            builders[i / 2].append(chars2[i]);
            builders[i / 2].append(chars2[i + 1]);
        }
        for (StringBuilder builder : builders) {
            System.out.println(builder.toString());
            char ch = (char) Integer.parseInt(builder.toString());
            System.out.println(ch);
        }
    }

    @Test
    public void test5() {
        int[] arr = {65539, 555655, 222, 555};
        String s = new String(arr, 0, 4);
        System.out.println(s.toCharArray().length);
        Map<Integer, Integer> map = new HashMap<>(10);
        map.put(2, 2);
        map.put(3, 2);
        map.put(4, 2);
        map.keySet().forEach((key) -> {
                    System.out.println("key: " + key + "  value: " + map.get(key));
                    System.out.println("ewqrr".intern());
                }
        );
        int num1 = 1;
        num1 <<= 8;
        System.out.println(num1);
        System.out.println("-------------------");
        byte[] bytes = {12, -55, 33, 45, 66};
        String s1 = new String(bytes, 5, 0, 4);
        System.out.println(s1);
    }

    @Test
    public void test6() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toInstant());
        Instant instant = date.toInstant();
        LocalDate now = LocalDate.now();
        System.out.println(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        System.out.println(format.format(date));
    }

    @Test
    public void test7() {
        // 保留2位小数
        double num = 100.2325242323;
        DecimalFormat decimalFormat = new DecimalFormat("##.###");
        System.out.println(decimalFormat.format(num));
        // 百分数
        double num2 = 0.23234223;
        DecimalFormat decimalFormat1 = new DecimalFormat(".###%");
        System.out.println(decimalFormat1.format(num2));
        // 格式化金钱
        double money = 122342134123421.3453;
        DecimalFormat decimalFormat2 = new DecimalFormat("#,###.####");
        System.out.println(decimalFormat2.format(money));
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update("sdfafa".getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = messageDigest.digest();
        System.out.println(Arrays.toString(digest));
        System.out.println("-------------------------");
        String s = new BigInteger(1, digest).toString(16).toUpperCase();
        System.out.println(s);
        System.out.println("-------------------------");
//        ArrayList<String> list = new ArrayList<>();
        char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        List<char[]> chars1 = Arrays.asList(chars);
        chars1.stream().forEach(System.out::println);
//        list.add("0");list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");
//        list.add("6");list.add("7");list.add("8");list.add("9");list.add("A");list.add("B");
//        list.add("C");list.add("D");list.add("E");list.add("F");
        StringBuilder builder = new StringBuilder();
        for (byte aByte : digest) {
            int low = aByte & 0xf;
            int high = (aByte >>> 4 )& 0xf;
            builder.append(chars[high]);
            builder.append(chars[low]);
        }
        System.out.println(builder.toString());
    }

    @Test
    public void test8(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(23);
        arrayList.add(33);
        // forEach 中实现了消费者接口，Consumer
        arrayList.forEach(integer -> {
            System.out.println(integer);
            System.out.println("hah");
        });
//        arrayList.removeIf(integer -> {
//            boolean a = integer> 30;
//            System.out.println(integer);
//            return a ;
//        });
        System.out.println(arrayList);
        Object[] objects = arrayList.toArray();
        System.out.println(objects);
        for (Object object : objects) {
            System.out.println(object.toString());
        }
        System.out.println();
        System.out.println("--------------------------");
//        Collections.
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1,3);


    }
}
