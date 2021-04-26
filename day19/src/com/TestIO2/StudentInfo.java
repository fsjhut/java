package com.TestIO2;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author:13
 * @className:StudentInfo
 * @description:
 * @date:2021/4/10 16:56
 * @version:0.1
 * @since:1.8
 */
@lombok.Getter
@lombok.Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentInfo implements Serializable {
    private static final long serialVersionUID = 1642130574851895783L;
    private    Integer id;
    private String name;
    private String gender;


//    @Override
//    public String toString() {
//        return "StudentInfo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", gender='" + gender + '\'' +
//                '}';
//    }
public void show(){
    System.out.println(getId()+"-"+getName()+"-"+getGender());
}
}
