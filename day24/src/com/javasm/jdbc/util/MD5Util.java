package com.javasm.jdbc.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author: DELL
 * @className: MD5Util
 * @description:
 * @date: 2021/4/19 15:04
 * @version:0.1
 * @since: 1.8
 */
public class MD5Util {

    private MD5Util() {
    }

    public static String encodeStr(String sourceStr) {
        Objects.requireNonNull(sourceStr);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            sourceStr = sourceStr + PropUtil.getValue("salt");
            messageDigest.update(sourceStr.getBytes(Charset.forName(PropUtil.getValue("str_encoding"))));

            byte[] digest = messageDigest.digest();

            return new BigInteger(1, digest).toString(16).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
