package com.TestIO2;


import java.io.*;
import java.util.*;

/**
 * @author:13
 * @className:t5Test
 * @description:
 * @date:2021/4/10 16:56
 * @version:0.1
 * @since:1.8
 */
public class t5Test {
    //    5. 创建一个学生类，包含属性：学号、姓名、性别，包含show（）方法用于显示学生的详细信息。
//    创建测试类，在控制台上显示添加学生信息，要求程序循环运行，并依次提示接收学生类的所有属性值，保存到学生对象中，再将学生对象保存到集合对象中，并提示“是否继续添加（y/n）：”,如果选择“y”则继续添加，
//    否则退出循环，并将保存学生数据的集合对象通过序列化保存到“student.dat”文件中。
//    实现从“student.dat”文件中反序列化保存学生数据的集合对象，并遍历打印输出学生信息。
    static Integer id = 1000;

    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo1() {
        ArrayList<StudentInfo> studentInfoList = new ArrayList<>(10);
        StudentInfo studentInfo = new StudentInfo();
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("day19/src/stu.dat"));
                Scanner input = new Scanner(System.in);
        ) {
            String answer;
            do {
                System.out.println("请输入学生姓名：");
                String studentName = input.next();
                System.out.println("请输入学生性别：");
                String studentGender = input.next();
                studentInfoList.add(new StudentInfo(id++, studentName, studentGender));
//                 studentInfo.show(id++, studentName, studentGender);

                System.out.println("是否继续录?y/n");
                answer = input.next();
            } while (Objects.equals("y", answer));
            objectOutputStream.writeObject(studentInfoList);
            for (StudentInfo info : studentInfoList) {
                System.out.println(info);
            }


//            studentInfoList
            System.out.println("序列化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demo2() {
        try (
                ObjectInput objectInput = new ObjectInputStream(new FileInputStream("day19/src/stu.dat"))
        ) {
            List<StudentInfo> studentInfoList = (List<StudentInfo>) objectInput.readObject();
//            Object o = objectInput.readObject();
            System.out.println(studentInfoList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


