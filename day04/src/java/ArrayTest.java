package java;

import java.util.Arrays;

/**
 * @author SunHang
 * @className: ArrayTest
 * @description TODO
 * @createTime 2021/3/18 16:48
 */
public class ArrayTest {
    public static void main(String[] args) {
        //删除数组元素
        String[] strings = {"a", "b", "c", "a", "c", "a", "hello"};//"b", "c", "a", "c", "a", "hello" null
        //[null,b,c,a]
        //删除数组里面所有的a元素  换成null
        //数组元素必须是连贯的  后面元素移动位置
        boolean isFlag = false;
        for (int index = 0, len = strings.length; index < len; index++) {
            if ("a".equals(strings[index])) {
                //后面元素依次移位
                for (int i = index; i < len - 1; i++) {
                    strings[i] = strings[i + 1];
                }
                if (!isFlag) {
                    strings[len - 1] = null;
                    isFlag = true;
//                }
//                isFlag = false;
                    //重复的元素
                    if ("a".equals(strings[index])) {
                        index--;
                    }
                }
            }
            //[null, b, null, hello, c, null, null]
        }
        System.out.println(Arrays.toString(strings));
    }
}


