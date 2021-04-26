package com.io.exec;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author SunHang
 * @className: StudentDemo
 * @description:
 * @createTime 2021/4/7 22:30
 */
public class StudentDemo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        test5();
        test9();

    }
    public static void test5(){
        HashMap<Integer, Student> map = new HashMap<>();
        String isFlag = "y";
        while ("y".equals(isFlag)) {
            System.out.print("请输入学生的姓名：");
            String name = scanner.next();
            System.out.print("请输入学生的年龄：");
            Integer age = scanner.nextInt();
            System.out.print("请输入学生的分数：");
            Integer score = scanner.nextInt();
            Student student = new Student(name, age, score);
            map.put(student.getId(), student);
            System.out.println("是否继续添加？(y/n)");
            isFlag = scanner.next();
        }
        // 序列化map
        try (
                ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream("E:\\demo\\student.dat"));

        ) {
            objectOutput.writeObject(map);
            System.out.println("序列化对象成功");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化
        try (
                ObjectInput input = new ObjectInputStream(new FileInputStream("E:\\demo\\student.dat"));
        ) {
            HashMap<Integer, Student> newMap = (HashMap<Integer, Student>) input.readObject();
            Set<Integer> integers = newMap.keySet();
            for (Integer integer : integers) {
                System.out.println(newMap.get(integer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test9(){
        String filePath = "E:\\demo\\student_info.txt";
        HashSet<Student> set = new HashSet<>();
        try (
                BufferedReader bufferInput = new BufferedReader(new FileReader(filePath));
        ) {
            String info;
            while ((info = bufferInput.readLine()) != null) {
                String[] strings = info.split("-");
//                System.out.println(strings);
                Student student = new Student(strings[0], Integer.parseInt(strings[1]),Integer.parseInt(strings[2]));
                set.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(set);
        List<Student> studentList = set.stream().sorted(new StudentComparator()).collect(Collectors.toList());
        studentList.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                System.out.println(student);
            }
        });
        studentList.forEach((stu)->{ System.out.println(stu); });
        studentList.forEach(System.out::println);

    }
}







