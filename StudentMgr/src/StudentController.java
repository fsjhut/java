import service.StudentService;
import util.InputUtil;
import util.Utils;
import view.Views;


/**
 * @author SunHang
 * @className: StudentController
 * @description:
 * @createTime 2021/4/28 13:37
 */
public class StudentController {
    private StudentService studentService = new StudentService();

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        controller.login();
    }

    public void login() {
        while (true) {
            Views.registerView();
            String choice = InputUtil.input("^[1-3]{1}$", "输入有误！");
            switch (choice) {
                case "1":
                    // 注册;
                    studentService.register();
                    break;
                case "2":
                    // 登录;
                    if(studentService.studentLogin()){
                        startMgr();
                    }
                    break;
                case "3":
                    // 退出
                    Utils.exitSystem();
                    break;
            }
        }
    }

    // 开启学生管理系统, 并展示学生管理系统菜单
    public void startMgr() {
        //Scanner sc = new Scanner(System.in);
        studentLoop:
        while (true) {
            Views.studentMgrView();
            String choice = InputUtil.input("^[0-5]{1}$", "请输入正确的操作编号: ");
            switch (choice) {
                case "1":
                    // System.out.println("添加");
                    studentService.addStudent();
                    break;
                case "2":
                    // System.out.println("删除");
                    studentService.deleteStudentById();
                    break;
                case "3":
                    // System.out.println("修改");
                    studentService.updateStudent();
                    break;
                case "4":
                    // System.out.println("查询");
                    studentService.findStudent();
                    break;
                case "5":
                    System.out.println("感谢您使用学生管理系统, 再见!");
                    break studentLoop;
                case "0":
                    Utils.exitSystem();
            }
        }
    }
}

