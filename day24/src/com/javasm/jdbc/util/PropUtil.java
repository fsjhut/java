package com.javasm.jdbc.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author: DELL
 * @className: PropUtil
 * @description:
 * @date: 2021/4/17 16:07
 * @version:0.1
 * @since: 1.8
 */
public class PropUtil {

    protected PropUtil() {
    }

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            Class.forName(getValue("jdbc.driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getValue(String key) {

        Objects.requireNonNull(key);
        return properties.getProperty(key, " ");
    }

}
