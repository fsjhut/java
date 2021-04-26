package com.test.exec;
/**
 * @className: Test4
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:16
 */


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

/**
 * 从控制台实现输入字符串（路径）
 * 分别把盘符，文件路径（不包含盘符），文件名，文件类型打出来
 * 如：E:\jboss-4.0.4.GA\server\minimal\conf\readme.txt
 * 解析路径，输入 盘符，文件路径，文件名，文件类型。
 * 盘符：E:
 * 文件路径：jboss-4.0.4.GA\server\minimal\conf\
 * 文件名：readme
 * 文件类型：txt
 */
@Getter
@Setter
@ToString
public class Test4 {
    private  String driveLetter;
    private  String path;
    private  String name;
    private  String type;

    public static void main(String[] args) {
        System.out.println("请输入一个文件路径:");
        String str = Test1.scanner.next();
//        String str = "E:\\jboss-4.0.4.GA\\server\\minimal\\conf\\readme.txt";
        Test4 test4 = new Test4();
        test4.test(str);
        System.out.println(test4);
    }
    public void test(String str){
        System.out.println("\\");
        String[] splitInfo = str.split("\\\\");
        System.out.println(Arrays.toString(splitInfo));
        driveLetter = splitInfo[0];
        String nameAndType = splitInfo[splitInfo.length-1];
        StringBuilder pathStr = new StringBuilder();
        for(int i = 1; i<splitInfo.length-1;i++){
            pathStr.append(splitInfo[i]);
            pathStr.append("\\");
        }
        path = pathStr.toString();
        String[] splitInfo2 = nameAndType.split("\\.");
        name = splitInfo2[0];
        type = splitInfo2[1];
    }
}
