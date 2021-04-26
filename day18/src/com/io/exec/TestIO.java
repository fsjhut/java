package com.io.exec;


import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import sun.security.util.ArrayUtil;

import java.io.*;
import java.nio.channels.CompletionHandler;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author SunHang
 * @className: Test
 * @description:
 * @createTime 2021/4/7 17:45
 */
public class TestIO {
    /**
     * 1. 使用IO技术，创建一个目录，然后复制一个文件到该目录
     * 2. 使用IO技术，开发出一个控制台的资源管理器！
     * 要求：从命令行输入一个路径！
     * 如果存在,将该目录下所有的文件和文件夹列举出来，
     * 如果不存在则输出不存在该路径。
     * 3. 基于转换流，从控制台输入一些字符串，
     * 并将该类信息保存到日志文件”log.txt”中去。
     * 4. 从控制台进行输入用户名以及用户密码，判断是否登录成功！
     * 要求准确的用户名和密码存在配置文件中！
     * 6. 已知文件a.txt文件中的内容为“AAbcdea22dferwplkCC321ou1”,
     * 请编写程序读取该文件内容，要求去掉重复字母(区分大小写字母)
     * 并按照自然排序顺序后输出到b.txt文件中。
     * 即b.txt文件内容应为”123ACabcdefklopruw”这样的顺序输出。
     * 7. 读取任意txt文件内容,并统计出这个文本中每个字符以及每个字符出现的次数，
     * 并以以下格式: 字符=次数 持久化保存文件中。
     * 8. 使用集合相关的功能,存储10个1-50(含50)的随机偶数元素,
     * 要求数字不能重复,添加完成后从大到小倒序遍历输出到控制台
     * 并使用IO流将集合中的元素按指定格式输出到当指定文件中,
     * 例如: 48,44,40,38,34,30,26……
     */
    public static final String PARENT = "E:\\demo";
    public static Scanner scanner = new Scanner(System.in);

    @Test
    public void test1() {
        File directory = new File(PARENT);
        File file = new File("E:\\Test.tcp");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            System.out.println("要复制的文件不存在！");
            return;
        }
        String targetFilePath = PARENT + File.separatorChar + file.getName();
        // 复制‪E:\Test.tcp 这个文件到demo下
        try (
                InputStream inputStream = new FileInputStream("E:\\Test.tcp");
                OutputStream outputStream = new FileOutputStream(targetFilePath)
        ) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
//                System.out.println(len);
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件复制成功！");
    }

    @Test
    public void test2() {
        System.out.println("请输入一个文件目录：");
        String directPath = scanner.next();
        File direct = new File(directPath);
        Objects.requireNonNull(direct);
        if (!direct.exists()) {
            System.out.println("输入的文件路径不存在！");
        }
        if (direct.isFile()) {
            System.out.println("该路径是一个文件！");
            System.out.println("该文件是： " + direct.getName());
        }
        // 输出所有的文件夹及文件目录。。利用递归
        getAllFile(direct, "| ");
    }

    @Test
    public void test3() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("E:\\demo\\log.txt")));
        ) {
            System.out.println("请录入数据(输入-1退出)：");
            while (true) {
                String str = bufferedReader.readLine();
                if ("-1".equals(str)) {
                    break;
                }
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            System.out.println("写入成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        String filePath = PARENT + File.separatorChar + "UserInfo.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("配置文件不存在！");
            return;
        }
        System.out.print("请输入你的用户名：");
        String name = scanner.next();
        System.out.print("请输入你的密码：");
        String pass = scanner.next();
        try (
                BufferedReader bufferInput = new BufferedReader(new FileReader(file));
        ) {
            String info;
            while ((info = bufferInput.readLine()) != null) {
                String[] infoArray = info.split("-");
                if (name.equals(infoArray[1]) && pass.equals(infoArray[2])) {
                    System.out.println("登录成功，欢迎你: " + infoArray[1]);
                    return;
                }
            }
            System.out.println("账号或密码错误，登录失败！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        // 读取文件，并转为字符串
        // 利用字符串的方法进行去重操作
        // 将去重后的数据写入到新的文件
        String filePath = PARENT + File.separatorChar + "a.txt";
        File file = new File(filePath);
        String destPath = PARENT + File.separatorChar + "b.txt";
        if (!file.exists()) {
            System.out.println("读取的文件不存在！");
            return;
        }
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))
        ) {
            String info;
            while ((info = (reader.readLine())) != null) {
//                List<Character> chars = new ArrayList<>();
//                System.out.println(newFile.toCharArray());
//                char[] chars = newFile.toCharArray();
                Character[] charObjectArray = info.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
                List<Character> characters = Arrays.asList(charObjectArray);
//                System.out.println(characters);
                List<Character> newChars = characters.stream().sorted().collect(Collectors.toList());
//                Collections.sort(chars);
                StringBuilder strTemp = new StringBuilder();
                for (Character newChar : newChars) {
                    strTemp.append(newChar);
                }
                String str = strTemp.toString();
                System.out.println(str);
                String newFile = str.replaceAll("(.)\\1+", "$1");
                System.out.println(newFile);
//                String newStr = new String();
                writer.write(newFile);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        String fileName = PARENT + File.separatorChar + "Test.tcp";
        String destName = PARENT + File.separatorChar + "CharCount.txt";
        try (
                Reader reader = new FileReader(fileName);
                BufferedWriter writer = new BufferedWriter(new FileWriter(destName))
        ) {
            int len;
            while ((len = reader.read()) != -1) {
                char ch = (char) len;
                if (hashMap.containsKey(ch)) {
                    Integer newNum = hashMap.get(ch) + 1;
                    hashMap.put(ch, newNum);
                } else {
                    hashMap.put(ch, 1);
                }
            }
            // 按照出现的次数进行降序排列
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(hashMap.entrySet()); //转换为list
            list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
            // 将hashMap写入writer
            for (Map.Entry<Character, Integer> entry : list) {
                Character key = entry.getKey();
                Integer value = entry.getValue();
                writer.write(key + "=" + value);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<Integer> set = new HashSet<>(16);
        int num;
        for (; set.size() < 10; ) {
            num = random.nextInt(1, 50);
            if (num % 2 == 0) {
                set.add(num);
            }
        }
        List<Integer> collect = set.stream().sorted(Comparator.comparing(i -> (Integer) i)
                .reversed()).collect(Collectors.toList());
        System.out.println(collect);
        // 写入到文件中
        String destFile = PARENT + File.separatorChar + "Random.txt";
        try (
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(destFile));
        ) {
            for (Integer integer : collect) {
                outputStream.write(String.valueOf(integer) +", ");
            }
            outputStream.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllFile(File direct, String str) {
        // 获取所有的文件或文件夹
        File[] fileList = direct.listFiles();
        Objects.requireNonNull(fileList);
        for (File file : fileList) {
            System.out.println(str + file.getName());
            if (file.isDirectory()) {
                getAllFile(file, "| " + str);
            }
        }
    }
}
