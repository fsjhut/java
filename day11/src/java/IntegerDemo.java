package java;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @className: IntegerDemo
 * @description:   
 * @author SunHang
 * @createTime 2021/3/29 10:43
 */
public class IntegerDemo {
    public static void main(String[] args) {
//        demo1();
//        System.out.println("----------");
//        demo2();
//        System.out.println("---------");
//        demo3();
//        class acls = Integer.class;
//        System.out.println(acls);
        ParseInt parseInt = new ParseInt();
        System.out.println(parseInt.getClass());
        Class<ParseInt> parseIntClass = ParseInt.class;
        System.out.println(parseIntClass);

        SoftReference<ParseInt> softReference = new SoftReference<>(parseInt);


        WeakReference<ParseInt> weakReference = new WeakReference<>(parseInt);
        System.out.println(weakReference.get());
        parseInt = null;
//        System.out.println(parseInt.toString());
        System.gc();
        System.out.println(weakReference.get());
    }

    private static void demo3() {
        Integer num1 = new Integer("177");
        Integer num3 = new Integer(200);
        Integer num6 = new Integer(200);
        int num2 = 177;
        Integer num4 = 200;
        Integer num5 = 200;
        System.out.println(num4==num5);
        System.out.println(num3==num4);
        System.out.println(num3==num6);
        System.out.println(num1);
        System.out.println(num1==num2);
//        System.out.println(num3==num2);
        System.out.println(num2==num3);
        System.out.println(num1==num3);
        String str = "12112";
//        int num6 = Integer.parseInt(str,16);
//        System.out.println(num6);
    }

    private static void demo2() {
        Integer num1 = 100; // 自动装箱
        Integer num2 = 100;
        int num3 = Integer.valueOf(100);
        num1 = 10;
        System.out.println(num2);
        Integer num4 = num3;
        Integer num5 = new Integer(num3);

        System.out.println(num1==num2);
        System.out.println(num4==num1);
        System.out.println(num4.compareTo(num5));
    }

    private static void demo1(){
        // 创建一个Integer对象
        Integer num1 = new Integer(100);
        Integer num2 = new Integer(100);

        System.out.println("********");
        System.out.println(num2);
        Integer num3 = new Integer("00");
        System.out.println(num1.toString());
        System.out.println(num2);
        System.out.println(num1==num2);
        System.out.println(num2 == num3);
    }

}
