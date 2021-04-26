package com.test.exec;
/**
 * @className: Test11
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:23
 */

/**
 * 让用户在控制台输入几个文件名，
 * 中间用“,”隔开，然后只把jpg文件换行输出来。
 * 例如：hello.tcp,a.txt,x1.jpg,t1.ppt,m.jpg
 */
public class Test11 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
//        String str = "hello.tcp,a.txt,x1.jpg,t1.ppt,m.jpg";
        System.out.println("请输入一个字符串: ");
        String str = Test1.scanner.next();
        String[] splitArr = str.split(",");
        for (String s : splitArr) {
            String[] splitArr2 = s.split("\\.");
            if(splitArr2[1].equals("jpg")){
                System.out.println(s);
            }
        }
    }
}
