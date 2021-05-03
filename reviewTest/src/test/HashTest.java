package test;

import org.junit.Test;

import java.util.*;

/**
 * @className: HashTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/29 22:11
 */
public class HashTest {
    @Test
    public void test1(){
        HashMap<Integer, Integer[]> map = new HashMap<>(5000);
//        HashSet<Integer[]> integers = new HashSet<>
        int tenNum = 0;
        int twentyNum = 0;
        int thirtyNum = 0;
       aa: while (map.size()<=5000){
            Integer[] ints = new Integer[7];
            for (int i = 0; i < 6; i++) {
                int j =  (int)(Math.random()*33+1);
                ints[i]=j;
            }
            ints[6] = (int)(Math.random()*16+1);
            for (int i = 0; i < 4; i++) {
                if(ints[i+1]==ints[i]+1&&ints[i+2]==ints[i]+2&&ints[i+3]==ints[i]+3){
                    continue aa;
                }
            }


       }
        Set<Map.Entry<Integer, Integer[]>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer[]> entry : entries) {
            if (entry.getKey() == 10) {
                tenNum++;
            }
            if (entry.getKey() == 20) {
                twentyNum++;
            }
            if (entry.getKey() == 30) {
                thirtyNum++;
            }
            Integer[] value = entry.getValue();
            for (Integer integer : value) {
                if (integer == 10) {
                    tenNum++;
                }
                if (integer == 20) {
                    twentyNum++;
                }
                if (integer == 30) {
                    thirtyNum++;
                }
            }

        }

    }
}
