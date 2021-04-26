package com.test.java2;
/**
 * @className: ReflectionTest
 * @description:
 * @author SunHang
 * @createTime 2021/4/4 16:39
 */

import com.test.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类指定的结构
 */
public class ReflectionTest {
    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;
        // 创建运行时类的对象
        Person p = (Person) clazz.newInstance();
        // 获取指定的属性 属性声明为public
        // 通常不采用此方法
        Field id = clazz.getField("id");
        // 设置当前属性的值
        // 参数1p:指定设置哪个对象的属性
        // 参数2:设置为多少
        id.set(p, 10001);
        // get
        int pId = (int) id.get(p);
        System.out.println(pId);

    }

    @Test
    public void testField1() throws Exception {
        Class clazz = Person.class;
        // 创建运行时类的对象
        Person p = (Person) clazz.newInstance();
        // 获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        // setAccessible必须置为true,才可以修改
        name.setAccessible(true);
        // 获取 设置指定对象的属性值
        name.set(p, "Tom");
        System.out.println(name.get(p));
    }

    @Test
    public void testMethod() throws Exception {
        Class clazz = Person.class;
        // 运行时类的对象
        Person p = (Person) clazz.newInstance();
        //1. 获取指定的某个方法 参数1: 指明获取方法的名字
        //                     参数2: 指明获取方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);
        // 可访问的
        show.setAccessible(true);
        // invoke:参数1: 方法的调用者 参数2: 给方法形参赋值的实参
        // invoke:invoke()方法的返回值即为对应类中调用的方法的返回值
        System.out.println(show.invoke(p, "CHN"));
        // 调用静态的方法
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        // 如果调用的运行时类中的方法没有返回值,则此invoke返回null
        Object returnVal = showDesc.invoke(Person.class);
//        Object returnVal2 = showDesc.invoke(null);
        System.out.println(returnVal); // null
    }

    /**
     * 如何调用指定的构造器
     *
     * @throws Exception
     */
    @Test
    public void testConstruct() throws Exception {
        Class clazz = Person.class;
        // 1. 获取指定构造器
        // 参数: 指明构造器的形参列表
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        // 2. 保证此构造器是可以访问的
        constructor.setAccessible(true);
        // 3. 调用此构造器创建运行时的对象
        Person per = (Person)constructor.newInstance("Tom");
        System.out.println(per);
    }
}
