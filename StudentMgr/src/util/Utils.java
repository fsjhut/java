package util;

import service.StudentService;

import java.time.LocalDateTime;

/**
 * @className: Utils
 * @description:   
 * @author SunHang
 * @createTime 2021/4/28 13:50
 */
public class Utils {
    public  static void exitSystem() {
        System.out.println("确认退出系统吗？(y/n)");
        String choice = InputUtil.input("[ynYN]{1}","输入错误，请重新输入");
        if ("y".equals(choice)) {
            System.out.println("系统退出，谢谢使用！");
            StudentService.logHash.put("exist",getTimeToString());
            StudentService.updateLogAndStudent();
            System.exit(0);
        }
    }
    public static String getTimeToString(){
        LocalDateTime nowTime = LocalDateTime.now();
        String timeString = nowTime.toString();
        String str = timeString.replace("T", " ");
        String[] split = str.split("\\.");
        return split[0];
    }


}
