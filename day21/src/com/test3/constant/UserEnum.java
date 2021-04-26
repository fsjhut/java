package com.test3.constant;
/**
 * @className: MyEnum
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 21:23
 */
public enum  UserEnum {
    // 本枚举类的对象 实例（常量 public static final）
    USER1(1,"user1"){
        // 相当于内部类，可以有自己的内部变量
        public int num = 100;
        // 重写UserEnum中的方法
        @Override
        public void demo(){
            System.out.println("user1-----demo---");
        }
    },
    USER2(2,"user2");
    // 现有对象
    // 枚举类 接口
    // 组成部分  成员变量 成员方法
    private Integer id;
    private String name;

    // 构造器 必须私有,且默认是私有的
    // 一般枚举类不提供 成员变量的set方法
//    UserEnum(){
//        System.out.println("无参构造-----");
//    }
    public void demo(){
        System.out.println("UserEnum....demo....");
    }
    UserEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

}
