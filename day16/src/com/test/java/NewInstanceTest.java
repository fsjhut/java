package com.test.java;
/**
 * @className: NewInstanceTest
 * @description:
 * @author SunHang
 * @createTime 2021/4/4 14:55
 */

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.junit.Test;

/**
 * 通过反射创建对应的运行时类的对象
 * 1. 运行时类必须提供一个空参的构造器
 * 2. 空参的构造器的访问权限足够,通常设置为public
 * 在javabean中要求提供一个public的空参构造器原因:
 * 1. 便于通过反射,创建运行时类的对象
 * 2. 便于子类继承此运行时类,默认调用super()时,保证父类有此构造器
 */
public class NewInstanceTest {

    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
