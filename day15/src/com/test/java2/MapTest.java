package com.test.java2;

import org.junit.Test;

import java.util.*;

/**
 * @author SunHang
 * @className: MapTest
 * @description:
 * @createTime 2021/4/4 20:08
 */
public class MapTest {
    public static void main(String[] args) {
        test4();
    }
    public void test5() {
        Map<String, Integer> map = new HashMap<>();
        map.put("SS", 123);
        map.put("34", 123);
        map.put("BB", 233);
        map.put("CC", 567);
        // 遍历所有的keySet
        // key 是一个set 不可重复 无序
        Set<String> strings = map.keySet();
        for (String string : strings) {
            Integer value = map.get(string);
            System.out.println("key: " + string + " value: " + value);
        }
        System.out.println("------------------");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = map.get(key);
            System.out.println("key: " + key + " value: " + value);
        }
        System.out.println("------------------------");
        // 遍历所有的value
        // value可重复,无序
        // 普通的Collections
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }
        System.out.println("-------------------------------");
        // 遍历所有的key-value
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entries.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Integer> next = iterator1.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println("key: " + key + " value: " + value);
        }
    }

    /**
     * 元素查询的操作：
     * Object get(Object key)：获取指定key对应的value
     * boolean containsKey(Object key)：是否包含指定的key
     * boolean containsValue(Object value)：是否包含指定的value
     * int size()：返回map中key-value对的个数
     * boolean isEmpty()：判断当前map是否为空
     * boolean equals(Object obj)：判断当前map和参数对象obj是否相等
     */

    public static void test4() {
        HashMap map = new HashMap();
        map.put("SS", 123);
        map.put("34", 123);
        map.put("BB", 233);
        map.put("CC", 567);
        map.remove("34");
        // 找不到返回null
        System.out.println(map.get("34"));
        // 返回boolean类型
        System.out.println(map.containsKey("AA"));
        System.out.println(map.containsValue(123));
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        map = new LinkedHashMap();
        System.out.println(map.put(123, "AA"));
        map.put(345,"BB");
        map.put(12,"CC");
        map.put(12,"DD");
        map.remove(12);
        System.out.println(map.put(12, "FF"));
        System.out.println(map);
        map.clear();
        System.out.println(map.size());

    }


}

