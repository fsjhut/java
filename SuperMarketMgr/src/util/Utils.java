package util;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Utils {
    public static int num;
    public static Scanner scanner = new Scanner(System.in);
    private Utils(){

    }
    public static String getImage(int id){
        String sql = "select img,path from image where id = ?";
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                Blob img = rs.getBlob("img");
                String path = rs.getString("path");
                InputStream binaryStream = img.getBinaryStream();
                // 检验路径是否存在，若不存在，则建立路径
                // C:\Users\Public\Desktop\picture\sdf.jpg
                String directory = path.substring(0, path.lastIndexOf("\\"));
                File direc = new File(directory);
                if(!direc.exists()){
                    direc.mkdirs();
                }
                FileOutputStream outputStream = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int len;
                while((len = binaryStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, len);
                }
                return path;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }
    public static int loginVerify(String name,String password){
        if(PropUtil.getValue("adminName").equals(name)&&
                PropUtil.getValue("adminPass").equals(password)){
            return 1;
        }else if(PropUtil.getValue("staffName").equals(name)&&
                PropUtil.getValue("staffPass").equals(password)){
            return 2;
        }
        return 0;
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
    public static  int getId(){
        String curDate = LocalDate.now().toString().replaceAll("-","");
        num++;
        String StrId = curDate + num;
        return Integer.parseInt(StrId);
    }
    public static String toJavaField(String str) {
        String[] split = str.split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]);// 拼接第一个字符
        // 如果数组不止一个单词
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                // 去掉下划线，首字母变为大写
                String string = split[i];
                String substring = string.substring(0, 1);
                split[i] = string.replaceFirst(substring, substring.toUpperCase());
                builder.append(split[i]);
            }
        }
        return builder.toString();
    }
    public static int uploadImage(String str) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = 0;
        // 根据一个本地路径，上传到数据库
        // 保存一个path
        File file = new File(str);
        if (!file.exists()) {
            return 0;
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            // 上传一个图片，得到图片的ID，并返回
            // 默认的位置是：C:\Users\Public\Desktop\picture
            StringBuilder path = new StringBuilder("C:\\Users\\JAVASM\\Desktop\\jpg\\");
            path.append(file.getName());
            String sql = "insert into image(path,img) values(?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, path.toString());
            ps.setBlob(2, inputStream);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getLong(1);
            }
            return (int) result;
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return 0;
    }
    public  static void exitSystem() {
        System.out.println("确认退出系统吗？(y/n)");
        char ch = scanner.next().charAt(0);
        if (ch == 'y') {
            System.out.println("系统退出，谢谢使用！");
            System.exit(-1);
        }
    }
}
