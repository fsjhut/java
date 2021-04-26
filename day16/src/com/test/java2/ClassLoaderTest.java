package com.test.java2;

import com.test.java1.Person;
import org.junit.Test;
import sun.security.jca.GetInstance;

import java.util.Random;

/**
 * @author SunHang
 * @className: ClassLoaderTest
 * @description:
 * @createTime 2021/4/4 22:02
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        // 对于自定义类,使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        // 调用系统类加载器, 获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
    }

    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        /**
         newInstance():调用此方法，创建对应的运行时类的对象。
         内部调用了运行时类的空参的构造器。

         要想此方法正常的创建运行时类的对象，要求：
         1.运行时类必须提供空参的构造器
         2.空参的构造器的访问权限得够。通常，设置为public。

         在javabean中要求提供一个public的空参构造器。原因：
         1.便于通过反射，创建运行时类的对象
         2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        Person obj = clazz.newInstance();
        System.out.println(obj);
    }

    @Test // 反射的动态性
    public void test3(){
        for(int i = 0; i<10;i++){
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "tcp.util.Date";
                    break;
                case 1:
                    classPath = "tcp.lang.Object";
                    break;
                case 2:
                    classPath = "com.test.java1.Person";
                    break;
            }
            try{
                Object instance = getInstance(classPath);
                System.out.println(instance);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public static Object getInstance(String classPath) throws Exception {
        Class clazz =  Class.forName(classPath);
        return clazz.newInstance();
    }
}
