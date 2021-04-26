package com.TestIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @className: Test11
 * @description:   
 * @author SunHang
 * @createTime 2021/4/10 21:22
 */
public class Test11 {
    static int idIndex = 1000;

    public static void main(String[] args) {
        demo3();
    }
    private static void demo3() {
        //序列化
        List<Student> studentList = new ArrayList<>(10);
        try (
                ObjectOutput output = new ObjectOutputStream(new FileOutputStream("day19/src/stu.dat"));
                Scanner input = new Scanner(System.in)) {
            String answer;
            do {
                System.out.println("录入姓名:");
                String stuName = input.nextLine();
                studentList.add(new Student(idIndex++, stuName));
                System.out.println("是否继续y/n");
                answer = input.nextLine();
            } while (Objects.equals("y", answer));
            for (Student student : studentList) {
                System.out.println(student);
            }
            output.writeObject(studentList);
            System.out.println("序列化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
