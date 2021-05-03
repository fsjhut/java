package view;

import util.InputUtil;

/**
 * @className: View
 * @description:   
 * @author SunHang
 * @createTime 2021/4/27 22:51
 */
public class Views {
    public static void registerView(){
        System.out.println("************欢迎使用黑马管理系统**************");
        System.out.println(" *             1. 注     册                *");
        System.out.println(" *             2. 登     录                *");
        System.out.println(" *             3. 退     出                *");
        System.out.println("*********************************************");
        System.out.print("请输入你的选择：");
//        return InputUtil.input("^[1-3]{1}$","请输入正确的操作编号: ");
    }
    public static void studentMgrView(){
        System.out.println("************欢迎使用黑马管理系统***************");
        System.out.println(" *             1. 增加学生信息               *");
        System.out.println(" *             2. 删除学生信息               *");
        System.out.println(" *             3. 修改学生信息               *");
        System.out.println(" *             4. 查找学生信息               *");
        System.out.println(" *             5. 返回上一级                 *");
        System.out.println(" *             0. 退出系统                   *");
        System.out.println("**********************************************");
        System.out.print("请输入你的选择：");
    }
    public static void findStudentView(){
        System.out.println("************欢迎使用黑马管理系统****************");
        System.out.println(" *             1. 根据Id查找                 *");
        System.out.println(" *             2. 根据姓名查找                *");
        System.out.println(" *             3. 根据手机号查找              *");
        System.out.println(" *             4. 显示所有学生信息            *");
        System.out.println("***********************************************");
        System.out.print("请输入你的选择：");
    }
}
