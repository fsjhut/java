package com.test.java;

import org.junit.Test;

/**
 * @className: ReflectionTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 13:28
 */

/**
 * 关于java.lang.Class类的加载
 * 1. 类的加载过程：
 * 程序经过javac.exe命令以后,会生成一个或者多个字节码文件
 * 接着我们使用java.exe命令对某个字节码文件进行解释运行.
 * 相当于将某个字节码文件加载到内存中.此过程为类的加载.
 * 加载到内存中的类称为运行时类,此运行时类,作为Class的一个实例
 * 2. Class的实例就对应这一个运行时类.
 * 3. 获取Class的实例的方式
 * 4. 加载到内存中的运行时类,会缓存一定的时间,在此时间之内,
 *    我们可以通过不同的方式获取此运行时类.
 */
public class ReflectionTest {
    @Test
    public void test() throws Exception{
        //方式一 加上泛型可以避免强转
        // 调用运行时类的属性 .class
        Class<Person> clazz = Person.class;
        // 通过运行时类的对象,调用getClass方法
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        // 通过Class的静态方法:forName(String classPath)
        Class clazz3 = Class.forName("com.test.java.Person");
        // 方式四:使用类的加载器
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.test.java.Person");


    }
}
