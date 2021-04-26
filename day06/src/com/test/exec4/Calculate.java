package com.test.exec4;
/**
 *@className: Calculate
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:49
 */

import java.util.Scanner;

/**
 * 描述一个计算器类 , 计算机有 操作数1 ， 操作数2 ，
 * 运算符 三个属性，还具备运算的功能。
 * 不能直接给 操作数1 操作数2，运算符直接赋值
 */
public class Calculate {
    double numberOne;
    double numberTwo;
    char opeartionChar;

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        Calculate calculate2 = new Calculate(2,4,'/');
        Scanner scanner = new Scanner(System.in);
        if(calculate2.opeartionChar=='\u0000'){
            System.out.print("请输入第一个数:");
            double num1 = scanner.nextDouble();
            System.out.print("请输入第二个数:");
            double num2 = scanner.nextDouble();;
            System.out.print("请输入操作符:");
            char ch = scanner.next().charAt(0);
            System.out.println(calculate.calculate(num1,num2,ch));
        }else{
            System.out.println(calculate2.calculate());

        }


    }

    public Calculate(double numOne, double numTwo, char opeartionCh) {
        this.numberOne = numOne;
        this.numberTwo = numTwo;
        this.opeartionChar = opeartionCh;
    }

    public Calculate() {
    }

    public double calculate(double num1,double num2, char opr){
        // 选择操作符
        double result = 0;
        switch (opr){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if(num2 == 0){
                    System.out.println("除数不能为0!");
                    System.exit(-1);
                }
                result = num1 / num2;
                break;
            case '%':
                if(num2 == 0){
                    System.out.println("除数不能为0!");
                    System.exit(-1);
                }
                result = num1 % num2;
                break;
            default:
                System.out.println("你输入的字符有误!");
                System.exit(-1);
        }
        return result;

    }

    public double calculate(){
        // 选择操作符
        double num1 = this.numberOne;
        double num2 = this.numberTwo;
        char opr = this.opeartionChar;
        return calculate(num1,num2, opr);
    }
}


