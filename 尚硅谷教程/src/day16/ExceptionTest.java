package day16;

import org.junit.Test;

/**
 * @author SunHang
 * @className: ExceptionTest
 * @description:
 * @createTime 2021/3/28 13:43
 */
public class ExceptionTest {
    //    @Test
    public static void test1() {

    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1};
        try {
            for(int i = 0; i<5;i++){
                System.out.println(arr[i]);
            }
        } catch (Exception e) {
            System.out.println("已捕获异常");
        }
        System.out.println("这是个异常,不是错误");
    }
}
