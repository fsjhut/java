package jdbc.util;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author SunHang
 * @className: FileUtil
 * @description:
 * @createTime 2021/4/19 16:55
 */
public class FileUtil {
    private static final String PARENT_DIRECTORY = "upload/user/";

    public static String fileUpload(String sourceFilePath) {
        Objects.requireNonNull(sourceFilePath);
//        File file = new File(sourceFilePath);
        String curDateStr = LocalDateTime.now().toString();
        File directory = new File(PARENT_DIRECTORY, curDateStr);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        try (
                FileInputStream inputStream = new FileInputStream(sourceFilePath);
                FileOutputStream outputStream = new FileOutputStream(new File(directory, fileName));
        ) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory.getPath() +File.separatorChar+ fileName;
    }
}
