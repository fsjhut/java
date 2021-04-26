package java;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @className: Test
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 11:41
 */
public class Test {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{12,63,-20,65,66};
//        String str1 = new String(bytes,"utf-8");
        String str2 = new String(bytes, Charset.forName("Utm8"));
        System.out.println(str2);

    }
}
