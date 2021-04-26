package util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropUtil {
    protected PropUtil() {
    }

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            Class.forName(getValue("driverClassName"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getValue(String key) {
        Objects.requireNonNull(key);
        return properties.getProperty(key, " ");
    }
}
