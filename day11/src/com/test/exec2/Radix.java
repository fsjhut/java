package com.test.exec2;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author SunHang
 * @className: Radix
 * @description:
 * @createTime 2021/3/29 21:34
 */
@Getter
public class Radix {
    private final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
    public static void main(String[] args) {
        System.out.println(Radix.toHexString(50));
        System.out.println(Radix.toBinaryString(20));
        System.out.println(Radix.toOctalString(20));
    }

    private static String toBinaryString(int i) {
        return toUnsignedString0(i, 1);
    }

    private static String toOctalString(int i) {
        return toUnsignedString0(i, 3);
    }

    private static String toHexString(int i) {
        return toUnsignedString0(i, 4);
    }

    private static String toUnsignedString0(int val, int shift) {
        // assert shift > 0 && shift <=5 : "Illegal shift value";
        int mag = 32 - numberOfLeadingZeros(val);
        int chars = Math.max(((mag + (shift - 1)) / shift), 1);
        char[] buf = new char[chars];

        formatUnsignedInt(val, shift, buf, 0, chars);

        // Use special constructor which takes over "buf".
        return String.valueOf(buf);
    }

    static int formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
        int charPos = len;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[offset + --charPos] = Radix.digits[val & mask];
            val >>>= shift;
        } while (val != 0 && charPos > 0);

        return charPos;
    }
    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }  // 若i<2^16则,i*2^16
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }  // 若i<2^8则, i*2^16*2^8
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }  // 若i<2^4则, i*2^16*2^8*2^4 本例20 不满足该条件
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }  // 若i<2^2则, i*2^16*2^8*2^2
//        n -= i >>> 31;
        int k = (i>>>31);
        n = n - k;
        return n;
    }
}



