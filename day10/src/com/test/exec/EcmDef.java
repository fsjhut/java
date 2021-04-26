package com.test.exec;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: EcmDef
 * @description:
 * @createTime 2021/3/28 16:02
 */
public class EcmDef {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个数字:");
        try {
            int i = scanner.nextInt();
            System.out.print("请输入第二个数字:");
            int j = scanner.nextInt();
            int result = ecm(i, j);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("数据类型不一致");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("缺少参数");
        } catch (ArithmeticException e) {
            System.out.println("除数为0");
        } catch (EcDef e) {
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("输入数字不合法");
        }
    }

    public static int ecm(int i, int j) throws EcDef {
        if (i < 0 || j < 0) {
            throw new EcDef("分子或者父母为负数!");
        }
        return i / j;
    }
}
