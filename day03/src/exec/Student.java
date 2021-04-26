package exec;

/**
 * @author : SunHang
 * @className: Student
 * @description:
 * @date: 2021/3/17 10:14
 * @version: 0.1
 * @since: 1.8
 */
public class Student {
    int number;
    int state;
    int score;
    public String info(){
        return "学号为：" + number + " 的学生"
                + "\t班级是： " + state + " 分数为：" + score;
    }
}
