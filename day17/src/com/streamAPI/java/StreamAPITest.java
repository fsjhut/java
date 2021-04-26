package com.streamAPI.java;
/**
 * @className: StreamAPITest
 * @description:
 * @author SunHang
 * @createTime 2021/4/6 16:53
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamAPI: 对java类库的最好的补充
 * 对集合的数据进行操作,类似于使用SQL执行的数据库查询
 * Collection是一种静态的内存数据结构
 * Stream是有关计算的
 * 关系型数据库自身提供了强大的增删改查功能,
 * 非关系型数据库需要使用该API对数据库中的内容进行处理
 * 特点: stream不会改变源对象,操作后返回一个有新结果的新的Stream
 *       延迟操作执行的,需要结果的时候才开始执行.
 * 步骤: 创建一个Stream对象
 *       中间操作
 *       终止操作(终端操作)
 *       特点: 一旦执行终止操作,则执行中间操作,产生结果,且不再被使用
 *       主要: 过滤与映射
 */
public class StreamAPITest {
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        // 创建一个streamAPI对象 串行流 // 有序
        Stream<Integer> stream = list.stream();
        // 并行流 无序
        Stream<Integer> parStream = list.parallelStream();
    }
    // 通过数组创建Stream
    @Test
    public void test2(){
        int[] arr= new int[]{1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);
        // Stream<Employee> stream1 = Arrays.stream(arr1);
    }
    // 通过Stream的of静态方法创建
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }
    // 创建无限流
    @Test
    public void test4(){
        //public static<T> Stream<T> Iterator(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
        //
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
