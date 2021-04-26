package jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @className: PropUtil
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:55
 */
public class PropUtil {
    private PropUtil(){}
    private static Properties properties;
    static{
        try {
            InputStream stream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
            properties.load(stream);
            Class.forName(getValue("jdbc_driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }
}
