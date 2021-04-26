package java;

import org.junit.Test;

/**
 * @className: ThrowTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/26 20:16
 */
public class ThrowTest {
    @Test
    public void aTest() {
        try{
            int x = 100;
            int y = 0;
            System.out.println("x/y=" + x/y);
            System.out.println("计算结束!");
        }catch (ArithmeticException e) {
            System.out.println("发生了数学异常,注意除数不能为0");

        }
    }
}
