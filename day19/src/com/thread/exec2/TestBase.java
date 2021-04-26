package com.thread.exec2;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @className: TestBase
 * @description:   
 * @author SunHang
 * @createTime 2021/4/9 21:59
 */
public class TestBase {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Test
    public void test1(){
        Integer[] arr1 = {23,233,22};
        HashMap<String, String> str = new HashMap<>();

//        String
//        arr2[2] = 4444;
        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));
    }
}
