package jdbc.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @className: MD5Util
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:55
 */
public class MD5Util {
    public static String encodeStr(String sourceStr) {
        try {
            Objects.requireNonNull(sourceStr);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            sourceStr = sourceStr + PropUtil.getValue("salt");
            md5.update(sourceStr.getBytes(Charset.forName(PropUtil.getValue("str_encoding"))));
            byte[] digest = md5.digest();
            return new BigInteger(1,digest).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
