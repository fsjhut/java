package com.test.exec;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;

/**
 * @className: Student
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 14:41
 */
@Setter
@Getter
public class Student implements Comparable {
    private Integer id;
    private Integer score;
    private static Random random = new Random();

    {
        this.id = random.nextInt(40);
        this.score = random.nextInt(100);
    }

    public Student() {

    }

    @Override
    public int compareTo(Object o) {
        Objects.requireNonNull(o);
        Student student;
        if(o instanceof Student){
            student = (Student)o;
            return Integer.compare(this.score,student.score);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", score=" + score +
                ", random=" + random +
                '}';
    }
}
