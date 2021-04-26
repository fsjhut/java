package com.test.java2;

import com.test.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author SunHang
 * @className: MethodTest
 * @description:
 * @createTime 2021/4/4 15:50
 */
public class MethodTest {

    @Test
    public void test1() {
        Class clazz = Person.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    /**
     * @注解 权限修饰符 返回值类型,方法名()
     */
    @Test
    public void test2() {
        // 获取方法声明时的注解
        Class clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            // 获取方法声明的注解
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
            // 获取权限修饰符
            Modifier.toString(declaredMethod.getModifiers());
            // 获取返回值类型
            System.out.println(declaredMethod.getReturnType().getName());
            // 获取方法名
            System.out.println(declaredMethod.getName());
            // 获取形参列表
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            if (parameterTypes == null && parameterTypes.length == 0) {
                System.out.println("没有形参");
            }
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType);
            }
            // 获取抛出的异常
            Class<?>[] exceptionTypes = declaredMethod.getExceptionTypes();
            for (Class<?> exceptionType : exceptionTypes) {
                System.out.println(exceptionType);
            }
        }
    }
    // 获取运行时类的带泛型的父类的泛型
    // 逻辑性代码 VS 功能性代码
    @Test
    public void test3(){
        Class clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
        // 获取泛型类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class) actualTypeArguments[0]).getName());

    }
}
