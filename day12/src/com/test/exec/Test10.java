package com.test.exec;
/**
 * @className: Test10
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:19
 */

/**
 * 练习：让用户在控制台不断录入英文字母（每次录入两个字母即可），
 * 直到总长度超过6个停止，然后统计一下一共有几个a
 */
public class Test10 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        int i = 0;
        char[] chars = new char[6];
        while (i<6){
            System.out.print("请输入两个英文字母:");
            String str = Test1.scanner.next();
            if(str.length()<2){
                System.out.println("输入非法!");
                continue;
            }
            if(str.matches("^[a-zA-Z]{2}$")){
                chars[i++] = str.toCharArray()[0];
                chars[i++] = str.toCharArray()[1];
            }else{
                System.out.println("输入非法!");
            }

        }
        int num = 0;
        for (char aChar : chars) {
            if(aChar == 'a'){
                num++;
            }
        }
        System.out.println("一共有: "+num+"个a");
    }
}
