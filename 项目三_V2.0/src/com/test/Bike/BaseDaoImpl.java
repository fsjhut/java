package com.test.Bike;

import java.util.Arrays;

/**
 * @author SunHang
 * @className: BaseDaoImpl
 * @description:
 * @createTime 2021/3/25 21:28
 */
public class BaseDaoImpl implements BaseDao {
    @Override
    public Object[] add(Object[] objArr, Object obj) {
        Object[] newObjArr = new Object[objArr.length + 1];
        for (int i = 0; i < objArr.length; i++) {
            newObjArr[i] = objArr[i];
        }
        newObjArr[objArr.length] = obj;
        return newObjArr;
    }

    @Override
    public Object[] delete(Object[] objArr, Object obj) {
        int objIndex = select(objArr, 2);
        int objLen = objArr.length;
        Object[] newObjArr = new Object[objArr.length - 1];
        for (int i = objIndex; i < objLen - 1; i++) {
            objArr[i] = objArr[i + 1];
        }
        for (int i = 0; i < objLen - 1; i++) {
            newObjArr[i] = objArr[i];
        }
        return newObjArr;
    }

    @Override
    public void update(Object[] objArr, Object obj) {
        int objIndex = select(objArr, 3);
        objArr[objIndex] = obj;
    }

    @Override
    public int select(Object[] objArr, int choice) {
//        for (int i = 0; i < objArr.length; i++) {
//            if (obj.equals(objArr[i])) {
//                return i;
//            }
//        }
        return choice-1;
    }

    /**
     * @param arrays
     * @return 数组元素大小从高到低的一个索引序列
     */
    @Override
    public  int[] rankList(int[] arrays) {
        int len = arrays.length;
        int[] newArr1 = Arrays.copyOf(arrays,len);
        int[] newArr2 = new int[len];
        for (int i = 0; i < len; i++) {
            int index = i;
            int temp = -1;
            for (int j = 0; j < len; j++) {
                if (newArr1[index] < newArr1[j]) {
                    index = j;
                }
            }
            newArr2[i] = index;
            newArr1[index] = temp;
        }
        return newArr2;
    }
}
