package test;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author SunHang
 * @className: HashMapTest
 * @description:
 * @createTime 2021/4/28 21:20
 */
public class HashMapTest {
    @Test
    public  void test() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "333");
        map.put(2, "333");
        map.put(3, "333");
        map.put(4, "333");
        map.put(5, "333");
        map.put(6, "333");
        map.put(7, "333");
        map.put(8, "333");
        map.put(9, "333");
        map.put(10, "333");
        map.put(11, "333");
        map.put(12, "333");
        map.put(13, "333");
    }
    @Test
    public void test2(){
        HashMap<Integer, String> map = new HashMap<>(20);
        map.put(33,"34333");
    }
    @Test
    public void test3(){
        int i  = 33&31;
        int j = (int) Math.pow(2,16);
        System.out.println(i);
        System.out.println(j);
    }
}
