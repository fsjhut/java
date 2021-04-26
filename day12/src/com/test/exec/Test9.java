package com.test.exec;
/**
 * @className: Test9
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:19
 */

import lombok.ToString;

import java.util.Arrays;

/**
 * 在控制台输入一个文件路径
 * （例如：C:\hello\image\hello.jpg），
 * 然后获得文件名（hello）和扩展名(jpg)
 * 注意双斜杠表示一个斜杠
 */
@ToString
public class Test9 {
    private  String name;
    private  String type;

    public static void main(String[] args) {
        System.out.println("请输入一个文件路径:");
        String str = Test1.scanner.next();
//        String str = "E:\\jboss-4.0.4.GA\\server\\minimal\\conf\\readme.txt";
        Test9 test9 = new Test9();
        test9.test(str);
        System.out.println(test9);
    }
    public void test(String str){
        System.out.println("\\");
        String[] splitInfo = str.split("\\\\");
        System.out.println(Arrays.toString(splitInfo));
        String nameAndType = splitInfo[splitInfo.length-1];
        String[] splitInfo2 = nameAndType.split("\\.");
        name = splitInfo2[0];
        type = splitInfo2[1];
    }
}
