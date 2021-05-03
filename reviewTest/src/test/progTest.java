package test;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @className: progTest
 * @description:   
 * @author SunHang
 * @createTime 2021/5/2 21:26
 */
public class progTest {
    @Test
    public void testDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String replace = localDateTime.toString().replace("T", " ");
        String[] split = replace.split("\\.");
        System.out.println(split.length);
        System.out.println(split[0]);
    }
}
