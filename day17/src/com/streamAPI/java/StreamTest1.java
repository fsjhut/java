package com.streamAPI.java;
/**
 * @className: StreamTest1
 * @description:   
 * @author SunHang
 * @createTime 2021/4/6 18:17
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试中间操作
 */
public class StreamTest1 {
    // 筛选与切片

    /**
     * filter(Predicate p)--接受lambda,从流中排出某些元素
     * limit(n)--截断流,使其元素不超过给定数量
     * skip(n)----跳过元素,返回一个扔掉了前n个元素的流,
     *            若流中的元素不足n个,则返回一个空流
     * distinct()----筛选,通过流所生成元素的hashCode()和equals()
     *               去除 重复元素.
     */
    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        // 创建一个streamAPI对象 串行流 // 有序
        // forEach 是结束语句,
        Stream<Integer> stream = list.stream();
        // 查询到大于3的数
        stream.filter(t->t>3).forEach(System.out::println);
        // 必须重新生成stream对象
        list.stream().limit(3).forEach(System.out::println);

        list.stream().skip(30).forEach(System.out::println);

        // 筛选 去重
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 映射
     * map(Function f): 接受一个函数作为参数,该函数会被应用于每个元素
     *                  上,将其映射成一个新的元素;
     * mapToDouble(ToDoubleFunction f): 接受一个函数作为参数,该函数会被
     *                                  应用到每个元素上,产生一个新的DoubleStream
     * mapToInt(ToIntFunction f):接受一个函数作为参数,...产生一个新的IntStream
     * mapToLong(ToLongFunction f): 接受一个函数作为参数,....产生一个新的LongStream
     * flatMap(Function f): 接受一个函数作为参数,将流中的每个值都换成另一个流
     *                      然后把所有的流链接成一个流.
     */
    @Test
    public void test2(){
        List<String> list = Arrays.asList("aa", "bbb", "cc", "dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        //
        Stream<Integer> integerStream = list.stream().map(String::length);
        integerStream.filter(len->len>2).forEach(System.out::println);
        // 不会打散,需要循环遍历
        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest1::fromStringToStream);
        streamStream.forEach(s->{
            s.forEach(System.out::println);
        });
        // flatMap()   打散
        Stream<Character> characterStream = list.stream().flatMap(StreamTest1::fromStringToStream);
        characterStream.forEach(System.out::println);
    }
    @Test
    public void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        ArrayList list2 = new ArrayList();
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.add(8);

//        list1.add(list2); //[1, 2, 3, 4, [5, 6, 7, 8]]
//        System.out.println(list1);
        list1.addAll(list2);
        System.out.println(list1); // [1, 2, 3, 4, 5, 6, 7, 8]
        // flatMap类似于addAll,会取出元素,再重新变成一个新的流
        // map类似于add,会将他们变成一个新的元素
    }
    // 将字符串中的字符转为字符集合对应的stream实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted() : 产生一个新流,其中按自然顺序排序
     * sorted(Comparator com) 产生一个新流,其中按比较器顺序排序
     */
    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(12, -44, 56, 73, 343, 233);
        list.stream().sorted().forEach(System.out::println);
        // 自定义类需要实现Comparable接口
        // 或者需要定制排序
        /**
         * list<Employee> employees = EmployeeDate.getEmployees();
         * employees.stream().sorted((e1,e2)->{
         *     return Integer.compare(e1.getAge(),e2.getAge());
         * })
         */
    }
}
