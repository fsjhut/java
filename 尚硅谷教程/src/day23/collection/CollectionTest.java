package day23.collection;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: CollectionTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/28 19:14
 */
@Getter
@Setter
public class CollectionTest {
    public static void main(String[] args) {
        String[] str = new String[5];
        for (String s : str) {
            s = "atguigu";
            System.out.println(s);
        }
        for(int i = 0; i<str.length;i++){
            System.out.println(str[i]);
        }
    }
}
