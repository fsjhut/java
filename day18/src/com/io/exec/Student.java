package com.io.exec;
/**
 * @className: Student
 * @description:   
 * @author SunHang
 * @createTime 2021/4/7 17:51
 */

/**
 * 5. 创建一个学生类，包含属性：学号、姓名、性别，
 * 包含show（）方法用于显示学生的详细信息。
 * 创建测试类，在控制台上显示添加学生信息，
 * 要求程序循环运行，并依次提示接收学生类的所有属性值，
 * 保存到学生对象中，再将学生对象保存到集合对象中，
 * 并提示“是否继续添加（y/n）：”,如果选择“y”则继续添加，
 * 否则退出循环，并将保存学生数据的集合对象通过序列化保存到“student.dat”文件中。
 * 实现从“student.dat”文件中反序列化保存学生数据的集合对象，
 * 并遍历打印输出学生信息。
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 9. 已知student_info.txt文件中有如下数据：（姓名-年龄-总分)
 * 张三-21-98
 * 李四-23-97
 * 王五-25-100
 * 赵六-15-100
 * 孙七-19-93
 * 运用IO技术获取将该文件中的数据分别封装成5个Student
 * (姓名为String类型,年龄为int类型,总分为int类型 )
 * 对象存入集合中（需要自己定义Student类）
 * 要求：根据学生的总分进行排序（降序），
 * 如果分数相同则比较年龄，年龄较大的排在前面。
 * 并显示排序之后的结果。
 */
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = 7326930823396640444L;
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;
    private static Integer num = 1001;

    public Student(String name, Integer age, Integer score) {
        this.id = num++;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
