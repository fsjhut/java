package com.LamdaTest;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import static com.LamdaTest.AInterface.NUM;

/**
 * @className: LamdaTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 19:23
 */
public class LamdaTest {
    //匿名内部接口对象
//    static AInterface aInterface = new AInterface() {
//        public int num  = 100;
//        @Override
//        public void a() {
//            System.out.println("aa----a");
//        }
//
//        @Override
//        public void b() {
//            System.out.println("bb-----b--");
//        }
//    };
    // 无参无返回值
    static AInterface aInterface = ()->System.out.println("-------Lamda--"+ NUM);
    // 有一个参数 有返回值
    static BInterface bInterface = str-> "wer"+str;
    // 有多个参数,有返回值
//    static CInterface cInterface = (str1,str2)->str1+str2;
    public static void main(String[] args) {
        aInterface.a();
        System.out.println();
        // 使用lamda调用无参无返回值的接口
        System.out.println(bInterface.b("hello"));
        CInterface cInterface = (str1,str2)->str1+str2;
        System.out.println(cInterface.c("Sun", "Hang"));

    }
}
