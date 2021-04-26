package com.io.exec;

import java.util.Comparator;

/**
 * @author: DELL
 * @className: StudentComparator
 * @description:
 * @date: 2021/4/6 10:04
 * @version:0.1
 * @since: 1.8
 */
public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student stu1, Student stu2) {
        int num = stu2.getScore().compareTo(stu1.getScore());
        if(num == 0){
            return stu2.getAge().compareTo(stu1.getAge());
        }
        return num;
    }
}
