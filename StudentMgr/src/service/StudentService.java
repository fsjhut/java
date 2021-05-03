package service;

import bean.Student;
import impl.StudentDaoImpl;
import util.InputUtil;
import util.MD5Util;
import util.PropUtil;
import util.Utils;
import view.Views;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SunHang
 * @className: StudentService
 * @description:
 * @createTime 2021/4/27 22:31
 */
public class StudentService {
    private static ArrayList<Student> stuList = new ArrayList<>();
    private static StudentDaoImpl studentDao = new StudentDaoImpl();
    public static BufferedWriter writer;
    public static ObjectInput objectInput;
    public static String lastLogin;
    public static HashMap<String, String> logHash = new HashMap<>();

    // 读取学生对象信息(从文件中读取)
    static {
        try {
            objectInput = new ObjectInputStream(new FileInputStream(PropUtil.getValue("filePath")));
            stuList = (ArrayList<Student>) objectInput.readObject();
//            stuList.forEach(System.out::println);
            writer = new BufferedWriter(new FileWriter(PropUtil.getValue("logPath"), true));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert objectInput != null;
                objectInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateLogAndStudent() {
        writeObj();
        logWrite();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 注册 注册成功后将信息保存到文件中
    public void register() {
        addStudent();
    }

    public boolean studentLogin() {
        int num = 3;
        while (true) {
            System.out.print("请输入学员Id：");
            String studentId = InputUtil.input();
            // 时间的验证
            if (!isLogin(studentId)) {
                System.out.println("请不要频繁登录，你的ID已被限制登录");
                return false;
            }
            System.out.print("请输入学员的密码：");
            String memberPass = InputUtil.input(".{6,10}", "密码格式错误");
            String pass = MD5Util.md5Encode(memberPass);
            if (stuList.size() == 0) {
                System.out.println("没有任何学员信息！");
                return false;
            }
            Student student = selectById(studentId);
            if(student==null){
                return false;
            }
            if (pass.equals(student.getPass())) {
                System.out.println("登录成功，欢迎" + student.getName());
                if(lastLogin!=null){
                    System.out.println("你的最后登录时间是："+lastLogin);
                }
                logHash.put("studentId", studentId);
                logHash.put("loginTime", Utils.getTimeToString());
                return true;
            } else {
                System.out.println("你输入的用户名或密码错误！");
                if (num != 1) {
                    num--;
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，程序结束!");
                    return false;
                }
            }
        }
    }

    // 添加学生方法
    public void addStudent() {
        // StudentService studentService = new StudentService();
        // 1. 键盘接收学生信息
        String id;
        while (true) {
            System.out.print("请输入学生id:");
            id = InputUtil.input();
            if (stuList.size() == 0) {
                break;
            }
            boolean flag = isExists(id);
            if (flag) {
                System.out.println("学号已被占用, 请重新输入");
            } else {
                break;
            }
        }
        Student stu = inputStudentInfo(id);
        // 3. 将学生对象,传递给StudentService(业务员)中的addStudent方法
        stuList.add(stu);
        HashMap<String, String> newStudentLog = new HashMap<>();
        newStudentLog.put("studentId", stu.getId());
        newStudentLog.put("registerTime", Utils.getTimeToString());
        newStudentLog.put("operatePerson", logHash.get("studentId"));
        logWrite(newStudentLog);
        System.out.println("添加成功");
    }

    // 修改学生方法
    public void updateStudent() {
        String updateId = inputStudentId();
        Student newStu = inputStudentInfo(updateId);
        try {
            studentDao.update(updateId, newStu, stuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("修改成功!");
        HashMap<String, String> updateStudentLog = new HashMap<>();
        updateStudentLog.put("updateId", updateId);
        updateStudentLog.put("updateTime", Utils.getTimeToString());
        updateStudentLog.put("operatePerson", logHash.get("studentId"));
        logWrite(updateStudentLog);
    }

    // 删除学生方法
    public void deleteStudentById() {
        String delId = inputStudentId();
        // 3. 调用业务员中的deleteStudentById根据id, 删除学生
        try {
            studentDao.deleteById(delId, stuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 4. 提示删除成功
        System.out.println("删除成功!");
        HashMap<String, String> delStudentLog = new HashMap<>();
        delStudentLog.put("deleteId", delId);
        delStudentLog.put("deleteTime", Utils.getTimeToString());
        delStudentLog.put("operatePerson", logHash.get("studentId"));
        logWrite(delStudentLog);
    }

    // 查看学生方法
    public void findStudent() {

        Views.findStudentView();
        String choice = InputUtil.input("[1-4]{1}", "输入信息有误");
        switch (choice) {
            case "1":
                selectById();
                break;
            case "2":
                selectByName();
                break;
            case "3":
                selectByPhone();
                break;
            case "4":
                selectAllStudent();
                break;
        }
    }

    private void selectAllStudent() {
        // 1. 调用业务员中的获取方法, 得到学生的对象数组
        // 2. 判断数组的内存地址, 是否为null
        if (stuList == null) {
            System.out.println("没有学生信息, 请添加后重试");
            return;
        }
        // 3. 遍历集合, 获取学生信息并打印在控制台
        for (Student aStuList : stuList) {
            System.out.println(aStuList);
        }
    }

    private void selectByPhone() {
        System.out.print("请输入你要查找学生的手机号:");
        String selectPhone = InputUtil.input("^1[3|4|5|7|8][0-9]{9}$", "手机号输入有误！");
        try {
            ArrayList<Integer> integers = studentDao.getIndex(selectPhone, stuList, "phone");
            integers.forEach((index) -> System.out.println(stuList.get(index)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectByName() {
        System.out.print("请输入你要查找学生的名字:");
        String selectName = InputUtil.input("\\w{2,10}", "含有非法字符/名字过长");
        try {
            ArrayList<Integer> students = studentDao.getIndex(selectName, stuList, "name");
            students.forEach((index) -> System.out.println(stuList.get(index)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectById() {
        System.out.print("请输入你要查找学生的ID:");
        String selectId = InputUtil.input();
        try {
            int index = studentDao.getIndex(selectId, stuList);
            System.out.println(stuList.get(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Student selectById(String selectId) {
        Student student = null;
        try {
            int index = studentDao.getIndex(selectId, stuList);
            if(index==-1){
                System.out.println("不存在该Id的学员!");
                return null;
            }
            student = stuList.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    private static void writeObj() {
        ObjectOutput objectOutput = null;
        try {
            objectOutput = new ObjectOutputStream(new FileOutputStream
                    (PropUtil.getValue("filePath")));
            objectOutput.writeObject(stuList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert objectOutput != null;
                objectOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 判断是否20分钟内是否登录三次

    private boolean isLogin(String studentId) {
        String str;
        String confirmTime;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(PropUtil.getValue("logPath")));
            flag:
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("\t");
                for (String s : split) {
                    String[] split1 = s.split("=");
                    if (("studentId").equals(split1[0]) && (studentId).equals(split1[1])) {
                        String[] split2 = str.split("\t");
                        for (String s1 : split2) {
                            String[] split3 = s1.split("=");
                            if ("loginTime".equals(split3[0])) {
                                builder.append(split3[1]);
                                builder.append("\t");
                                continue flag;
                            }
                        }

                    }
                }
            }
            String[] strings = builder.toString().split("\t");
            // 没有登录过
            if (strings.length == 0) {
                return true;
            }
            // 获取最后登录时间
            lastLogin = strings[strings.length - 1];
            // 如果获取记录超过两条，则进行登录验证，即：20分钟只能登录三次
            if (strings.length > 1) {
                confirmTime = strings[strings.length - 2];
                return isCanlogin(confirmTime);
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isCanlogin(String confirmTime) {
        // 将时间对象转为date，然后与当前时间比较
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date confirmData = simpleDateFormat.parse(confirmTime);
            Date nowData = new Date();
            if ((nowData.getTime() - confirmData.getTime()) >= 1200000) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 退出时进行写入操作
    public static void logWrite() {
        Set<Map.Entry<String, String>> entries = logHash.entrySet();
        try {
            for (Map.Entry<String, String> next : entries) {
                writer.write(next.getKey() + "=");
                writer.write(next.getValue() + "\t");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 增删学员信息的时候
    public static void logWrite(HashMap<String, String> newStudentHash) {
        Set<Map.Entry<String, String>> entries = newStudentHash.entrySet();
        try {
            for (Map.Entry<String, String> next : entries) {
                writer.write(next.getKey() + "=");
                writer.write(next.getValue() + "\t");
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 键盘录入学生id
    public String inputStudentId() {
        String id;
        while (true) {
            System.out.print("请输入学生id:");
            id = InputUtil.input();
            boolean exists = isExists(id);
            if (!exists) {
                System.out.println("您输入的id不存在, 请重新输入:");
            } else {
                break;
            }
        }
        return id;
    }

    // 键盘录入学生信息
    public Student inputStudentInfo(String id) {
        //    private String id;
        //    private String name;
        //    private String gender;
        //    private Integer age;
        //    private String phone;
        //    private Integer group;
        //    private Date birthday;
        System.out.print("请输入学生的密码:");
        String pass = InputUtil.input(".{6,10}", "密码格式错误");
        System.out.print("请输入学生姓名:");
        String name = InputUtil.input("\\w{2,10}", "含有非法字符/名字过长");
        System.out.print("请输入性别：");
        String gender = InputUtil.input("[男|女]{1}");
        System.out.print("请输入年龄:");
        Integer age = InputUtil.inputInt();
        System.out.print("请输入手机号:");
        String phone = InputUtil.input("^1[3|4|5|7|8][0-9]{9}$", "手机号输入有误！");
        System.out.print("请输入你的分组:");
        int group = InputUtil.inputInt();
        System.out.print("请输入生日:");
        Date birthday = InputUtil.inputDate();
        pass = MD5Util.md5Encode(pass);
        return new Student(id, pass, name, gender, age, phone, group, birthday);
    }

    private boolean isExists(String id) {
        try {
            int tmp = studentDao.getIndex(id, stuList);
            if (tmp == -1) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
