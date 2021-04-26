package com.test.java2;
/**
 * @className: FieldTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 15:36
 */

import com.test.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 */
public class FieldTest {
    @Test
    public void test1(){
        Class clazz = Person.class;
        //获取属性结构
        //getFields():获取当前运行时类及其父类
        // 声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // getDeclaredField 获取当前运行时类自己的所有属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 1. 权限修饰符/0: 默认,
            int modifiers = declaredField.getModifiers();
            System.out.println(Modifier.toString(modifiers));
            // 2. 数据类型
            Class<?> type = declaredField.getType();
            System.out.println(type.getName());
            // 3. 变量名
            String name = declaredField.getName();
            System.out.println(name);
            System.out.println(declaredField);
        }
        //
    }
}
