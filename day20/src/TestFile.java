import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author SunHang
 * @className: TestFile
 * @description:
 * @createTime 2021/4/12 21:23
 */
public class TestFile {
    public static void main(String[] args) {
        demo3();
        System.out.println("上传成功！");
    }

    private static void demo3() {

        //创建File_目录(文件夹)

        File directory = new File("day20/src/upload");
        System.out.println(directory.exists());
        System.out.println(directory.getName());
        System.out.println(directory.getPath());
        System.out.println(directory.getAbsolutePath());
        String direcPath = directory.getAbsolutePath();
        if (!directory.exists()) {
            directory.mkdir();//创建多级目录
        }
        File file = new File("day20/src/upload/23");
        String absolutePath = file.getAbsolutePath();
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        try {
            FileInputStream inputStream = new FileInputStream(file);
            System.out.println(inputStream.read());
            System.out.println("----------------");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String str = absolutePath.replace(direcPath, "");
        System.out.println(str);
        String truePath2 = str.substring(1,str.length());
        System.out.println(truePath2);

//        System.out.println(directory.delete());//删除目录  有子级 无法删除

//        System.out.println(directory.isDirectory());
    }
}
