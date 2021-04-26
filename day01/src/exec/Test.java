package exec;

/**
 * @author : SunHang
 * @className: Test
 * @description:
 * @date: 2021/3/15 19:19
 * @version: 0.1
 * @since: 1.8
 */
public class Test {
    public static void main(String[] args){
        System.out.println("*	*");
        System.out.println('*' + '\t' + '*');//全部为字符运算，结果为int
        System.out.println('*' + "\t" + '*');//可以
        System.out.println('*' + '\t' + "*");//51*前两个为字符运算，结果为int
        System.out.println('*' + ('\t' + "*"));//可以
        int num = 0;
        num = num+(num++)+(++num);
        System.out.println(num);
        num = num++;
        // temp = num ; temp = 2
        // num ++;
        // num = temp; num=2;
        System.out.println(num);
        // 位运算符交换数字
        int num1 = 10;
        int num2 = 30;
        num1 = num1 ^ num2; // num1 = 10 ^ 30
        num2 = num1 ^ num2; // num2 = 10 ^ 30 ^30 = 10
        num1 = num1 ^ num2; // num1 = 10 ^ 30 ^ 10 = 30
        System.out.println("num1=" + num1 + " num2=" + num2);

    }
}
