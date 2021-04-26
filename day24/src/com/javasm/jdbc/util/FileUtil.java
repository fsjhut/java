package com.javasm.jdbc.util;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: DELL
 * @className: FileUtil
 * @description:
 * @date: 2021/4/19 14:50
 * @version:0.1
 * @since: 1.8
 */
public class FileUtil {

    private FileUtil() {
    }

    private static final String PARENT_DIRECTORY = "upload/user/";

    /**
     * 文件上传
     *
     * @param sourceFilePath 源文件路径
     * @return 服务器上面的文件的路径
     */
    public static String fileUpload(String sourceFilePath) {
        Objects.requireNonNull(sourceFilePath);

        File file = new File(sourceFilePath);
        String curDateStr = LocalDate.now().toString();

        //day24/upload/user/2020-01-01/a.jpg
        File directory = new File(PARENT_DIRECTORY, curDateStr);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "-" + file.getName();
        try (
                //1.创建流对象
                InputStream inputStream = new FileInputStream(sourceFilePath);
                OutputStream outputStream = new FileOutputStream(new File(directory, fileName));
        ) {
            //2.循环读写
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
