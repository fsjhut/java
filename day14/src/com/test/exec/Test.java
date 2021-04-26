package com.test.exec;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SunHang
 * @className: Test
 * @description:
 * @createTime 2021/4/3 13:55
 */
public class Test {
    public static Random random = new Random();
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    private static void test6() {
        List<Account> list = new ArrayList<>();
        list.add(new Account(10.00, "1234"));
        list.add(new Account(15.00, "5678"));
        list.add(new Account(0, "1010"));
//        System.out.println(Arrays.toString(list.toArray()));
        HashMap<Long,Account> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getId(),list.get(i));
        }
//        System.out.println(map);
        Set<Long> ids = map.keySet();
        for (Long id : ids) {
            System.out.println("{id是：" + id + " 余额是：" + map.get(id));
        }
    }

    private static void test5() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("科特迪瓦");
        arrayList.add("阿根廷");
        arrayList.add("澳大利亚");
        arrayList.add("塞尔维亚");
        arrayList.add("荷兰");
        arrayList.add("尼日利亚");
        arrayList.add("日本");
        arrayList.add("美国");
        arrayList.add("中国");
        arrayList.add("新西兰");
        arrayList.add("巴西");
        arrayList.add("比利时");
        arrayList.add("韩国");
        arrayList.add("喀麦隆");
        arrayList.add("洪都拉斯");
        arrayList.add("意大利");
        Collections.shuffle(arrayList);
        ArrayList<String> arrayList1 = new ArrayList<>(4);
        ArrayList<String> arrayList2 = new ArrayList<>(4);
        ArrayList<String> arrayList3 = new ArrayList<>(4);
        ArrayList<String> arrayList4 = new ArrayList<>(4);
        for(int k=0;k<arrayList.size();k++){
            if(k<4){
                arrayList1.add(arrayList.get(k));
            }else if(k<8){
                arrayList2.add(arrayList.get(k));
            }else if(k<12){
                arrayList3.add(arrayList.get(k));
            }else{
                arrayList4.add(arrayList.get(k));
            }
        }
        System.out.print("第一组：");
        arrayList1.forEach(str-> System.out.print(str+"\t\t"));
        System.out.print("\n第二组：");
        arrayList2.forEach(str-> System.out.print(str+"\t\t"));
        System.out.print("\n第三组：");
        arrayList3.forEach(str-> System.out.print(str+"\t\t"));
        System.out.print("\n第四组：");
        arrayList4.forEach(str-> System.out.print(str+"\t\t"));
    }

    private static void test4() {
        TreeSet<Student> treeSet = new TreeSet<>();
        for(; treeSet.size()< 20;){
            treeSet.add(new Student());
        }
        Iterator<Student> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void test3() {
        String str = "2021-4-3";
        String format = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = Calendar.DAY_OF_WEEK -1 ;
        // 集合
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"星期一");
        map.put(2,"星期二");
        map.put(3,"星期三");
        map.put(4,"星期四");
        map.put(5,"星期五");
        map.put(6,"星期六");
        map.put(7,"星期日");
        System.out.println(str+ " 是: " + map.get(index));
    }

    private static void test2() {
        HashMap hashMap = new HashMap();
        Programmer pro1 = new Programmer("sun", 1001, '男', 2000.);
        Programmer pro2 = new Programmer("hang", 1002, '男', 3000.);
        Manager mag1 = new Manager("ssss", 2001, '男', 5000., 2000.);
        Manager mag2 = new Manager("hhhh", 2002, '男', 6000., 5000.);
        hashMap.put(pro1.getId(),pro1);
        hashMap.put(pro2.getId(),pro2);
        hashMap.put(mag1.getId(),mag1);
        hashMap.put(mag2.getId(),mag2);
        // hashMap的遍历 利用forEach方法
        Set entries = hashMap.entrySet();
        entries.forEach(System.out::println);
        System.out.println("------------");
        // 利用迭代器
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Set set = hashMap.keySet();
        for (Object key : set) {
            System.out.println("key是：" + key +", value是："+ hashMap.get(key));
        }

    }

    public static void test1() {
        ArrayList<Staff> array = new ArrayList<>(10);
        Programmer pro1 = new Programmer("sun", 1001, '男', 2000.);
        Programmer pro2 = new Programmer("hang", 1002, '男', 3000.);
        Manager mag1 = new Manager("ssss", 2001, '男', 5000., 2000.);
        Manager mag2 = new Manager("hhhh", 2002, '男', 6000., 5000.);
        array.add(pro1);
        array.add(pro2);
        array.add(mag1);
        array.add(mag2);
        // 利用itertor进行迭代
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
