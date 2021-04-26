package com.thread.java;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author SunHang
 * @className: DownloadNovelUtil
 * @description:
 * @createTime 2021/4/8 21:03
 */
public class DownloadNovelUtil {
    private DownloadNovelUtil() {

    }

    public static void downloadNovel(String novelPath, String targetFilePath) {
        Objects.requireNonNull(novelPath);
        Objects.requireNonNull(targetFilePath);
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (new URL(novelPath).openStream(), Charset.defaultCharset()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath));
        ) {
            String str;
            while ((str = reader.readLine())!=null){
                if(str.contains("class=\"read-content j_readContent\"")){
                    writer.write(reader.readLine().replaceAll("<p>", "\n"));
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
