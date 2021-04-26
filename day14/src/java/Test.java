package java;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author SunHang
 * @className: Test
 * @description:
 * @createTime 2021/4/2 14:00
 */
public class Test {
    public static void main(String[] args) {
//        test1();
        ArrayList<String> array = new ArrayList<>();
        array.add("sa");
        array.add("sb");
        array.add("sb");
        array.add("sn");
        array.add("sb");
        array.add("gg");
//        test2(array);
//        test3();
    }

//    private static void test3() {
//    }

    private static void test2(Collection<String> collection) {
        Objects.requireNonNull(collection);
        if(collection.isEmpty()){
            return;
        }
        System.out.println(collection.size());
        System.out.println(collection);
        collection.remove("sn");
        System.out.println(collection);
        // 删除所有的sb
        // contains(): 判断集合中是否包含某个元素,返回值是boolean
        System.out.println(collection.contains("sb"));
        // 在增强for循环中，如果直接调用remove方法，
        // 则会出现checkForComodification 异常
//        for (String str : collection) {
//            if(str==null){
//                continue;
//            }
//            if("sb".equals(str)){
//                System.out.println(collection.remove(str));
//            }
//        }
        // 基于迭代器可以进行删除和修改
        Iterator<String>iterator = collection.iterator();
//        while (iterator.hasNext()){
//            String str= iterator.next();
//            if(str == null){
//                continue;
//            }
//            if("sb".equals(str)){
//                iterator.remove();
//            }
//        }
        // 删除某个元素
        collection.removeIf(str->Objects.equals("sb",str));
        System.out.println(collection);
        String[] str;
        str = collection.toArray(new String[0]);
        System.out.println(Arrays.toString(str));
        Map map = new HashMap();
    }

    public static void test1() {
        ArrayList<String> array = new ArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");
        System.out.println(Arrays.toString(array.toArray()));
        array.remove("b");
        System.out.println(Arrays.toString(array.toArray()));
//        array.clear();
        System.out.println(Arrays.toString(array.toArray()));
        // 遍历的操作 1. 增强for循环
        for (String s : array) {
            System.out.println(s);
        }
        // 第二种遍历的方法：Iterator
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
        // 第三种利用forEach方法
        array.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("元素：" + s);
            }
        });
        // 利用lambda表达式来计算
        array.forEach(s -> System.out.println("元素" + s));
    }
}
