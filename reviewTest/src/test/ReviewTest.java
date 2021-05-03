package test;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author SunHang
 * @className: ReviewTest
 * @description:
 * @createTime 2021/4/23 21:00
 */
public class ReviewTest {
    @Test
    public void test1() {
        String str = "23345";
        int anInt = Integer.parseInt(str, 16);
        // 16进制字符串转为十进制整数
        // 如何将输出一个16进制的数呢？
        System.out.println(anInt);
        byte[] bytes = new byte[]{-33, -44, 22, -55, 99, 30, 22, 55};
        ArrayList<String> list = new ArrayList<>();

//        map.put(1,"333");
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        String string = new BigInteger(1, bytes).toString(16).toUpperCase();
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            int lowValue = aByte & 0xf;
            int highValue = (aByte >> 4)& 0xf;
            builder.append(list.get(highValue));
            builder.append(list.get(lowValue));
        }
//        int aByte = (int)bytes[1] & 15;  // 可以获取低四位的数据
//        String finalValue = list.get(aByte);
//        System.out.println(aByte);
        System.out.println(builder.toString());
        System.out.println(string);
        System.out.println("abc".getClass());
        Integer a = 2;
        System.out.println(a.getClass());
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.removeIf(value->value<5);
        System.out.println(arrayList);
        arrayList.set(2,44);
        System.out.println(arrayList);

    }
    @Test
    public void test2(){
        Random random = new Random();
        ThreadLocalRandom current = ThreadLocalRandom.current();
        IntStream ints = random.ints(5, 1, 31);
        ints.forEach(System.out::println);
    }
    @Test
    public void test3(){
        HashMap<Integer, String> map = new HashMap<>(10);
        map.put(1,"333");
    }

}
