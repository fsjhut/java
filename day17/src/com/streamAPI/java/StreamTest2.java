package com.streamAPI.java;
/**
 * @className: StreamTest2
 * @description:
 * @author SunHang
 * @createTime 2021/4/6 19:22
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Stream的终止操作
 * 1. 匹配与查找
 * 1. allMatch(Predicate p)--->检查是否匹配所有元素
 * 2. anyMatch(Predicate p)---> 检查是否至少匹配一个元素
 * 3. noneMatch(Predicate p)---->检查是否没有匹配的元素
 * 4. findFirst------------->返回第一个元素
 * 5. findAny---------------->返回当前流中的任意元素
 * 6. count------------------> 返回流中的元素总数
 * 7. max(Comparator c)------> 返回流中最大值
 * 8. min(Comparator c)------> 返回流中的最小值
 * 9. forEach(Consumer c)------> 内部迭代
 * 2.
 */
public class StreamTest2 {
    //
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(12, -44, 56, 73, 343, 233);
        System.out.println(list.stream().filter(e -> e > 20).count());
        System.out.println(list.stream().findAny());
        Stream<Integer> integerStream = list.stream().map(e -> e * 2);
        Optional<Integer> max = integerStream.max(Integer::compare);
        System.out.println(max);
        // 内部迭代
        list.stream().forEach(System.out::print);
        System.out.println();
        list.forEach(System.out::print);
    }

    /**
     * 规约:
     * reduce(T iden,BinaryOperator b): 可以将流中的元素反复结合起来
     * 得到一个值,返回T
     * reduce(BinaryOperator b): 可以将流中的元素反复结合起来,得到一个值
     * 返回Optional<T>
     * map和reduce的连接通常称为map-reduce模式
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(12, -44, 56, 73, 343, 233);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // er
    }

    /**
     * 收集
     * collect(Collector c) 将流转换为其它形式,接受一个Collector
     * 接口的实现,用于给Stream中元素做汇总的方法
     * Collector 接口方法的实现决定了如何对流执行收集的操作(如收集到的List
     * Set  Map)
     * Collectors实用类提供了很多的静态方法,可以方便的创建收集器实例
     */


    /**
     * Optional类
     * 最常见的异常类型是空指针异常,因此通过Optional类来避免空指针异常
     * Optional<T>类,是一个容器类,它可以保存类型T的值,代表这个值存在
     * 或者仅仅保存null,代表这个值不存在,可以避免空指针异常
     * javadoc的描述: 这是一个可以为null的容器,如果值存在则isPresent()方法
     * 返回true, 调用get()方法会返回该对象
     * 常用方法:
     * 1. Optional.of(T t): 创建一个Optional实例,t必须非空
     * 2. Optional.empty(): 创建一个空的Optional实例
     * 3. Optional.ofNullable(T t): t 可以为null
     * 判断Optional容器中是否包含对象?
     * boolean isPresent(): 判断是否包含对象
     * void ifPresent(Consumer<? super T> consumer): 执行Consumer接口
     * 的实现代码,并且该值会作为参数传给它
     * 获取Optional容器的对象:
     * T get():如果调用对象包含值,返回该值,否则抛出异常
     * T orElse(T other): 如果有值则将其返回,否则返回指定的other对象
     * T orElseGet(Supplier<? extends T> other) : 如果有值则将其返回,否则返回由
     * Supplier接口实现提供的对象
     * T orElseThrow(Supplier<? extends X> exceptionSupplier): 如果有值
     * 则将其返回,否则则抛出由Supplier接口实现提供的异常.
     */
    @Test
    public void test4() {
//        Girl girl = new Girl();
        Girl girl = null;
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1);
    }

    @Test
    public void test5() {
        Girl girl = new Girl();
//        Girl girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);
        Girl girl2 = girl1.orElse(new Girl("sun"));
        System.out.println(girl2);
    }

    public static String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    // 需要优化
    public static String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    // 使用Option类进行优化
    public static String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy hhhh = boyOptional.orElse(new Boy(new Girl("hhhh")));
        Girl girl = hhhh.getGirl();
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        // girl2 一定非空
        Girl girl2 = girl1.orElse(new Girl("sss"));
        String name = girl2.getName();
        System.out.println(name);
        return name;
    }


    @Test
    public void test6() {
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
//        boy.orElse()
        System.out.println(girlName);
    }
}
