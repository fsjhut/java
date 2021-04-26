package exec;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: LuckyDraw
 * @description
 * @createTime 2021/3/18 20:38
 */

public class LuckyDraw2 {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRegister;
    private static boolean isLogin;
    private static final int NUM = 10;
    private static int index = 0;
    private int trueNo;

    public static void main(String[] args) {
        LuckyDraw2 luckyDraw = new LuckyDraw2();
        Member2[] arr = new Member2[NUM];
        boolean isFlag = true;
        while (isFlag) {
            luckyDraw.viewMenu();
            System.out.print("请输入操作编号：");
            int num = scanner.nextInt();
            switch (num) {
                case (1):
                    System.out.println("[幸运抽奖系统> 注册]");
                    luckyDraw.register(arr);
                    break;
                case (2):
                    System.out.println("[幸运抽奖系统> 登录]");
                    if (isRegister) {
                        if (isLogin) {
                            System.out.println("你已经登录本系统！");
                            System.out.println("是否要重新登陆？(y/n)");
                            char flag = scanner.next().charAt(0);
                            if (flag == 'y') {
                                // 退出登陆账号
                                isLogin = false;
                                // 重新登陆
                                luckyDraw.login(arr);
                            }
                        } else {
                            luckyDraw.login(arr);
                        }
                    } else {
                        System.out.println("请先注册本抽奖系统！");
                    }
                    break;
                case (3):
                    System.out.println("[幸运抽奖系统> 抽奖]");
                    if (isLogin) {
                        luckyDraw.lottery();
                    } else {
                        System.out.println("请你先登录本系统！");
                    }
                    break;
                case (4):
                    if (isLogin) {
                        luckyDraw.viewMember(arr);
                    } else {
                        System.out.println("请你先登录本系统！");
                    }
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }
            System.out.println("继续吗？(y/n)");
            char ch = scanner.next().charAt(0);
            if (ch != 'y') {
                isFlag = false;
                System.out.println("系统退出，谢谢使用！");
            }
        }

    }

    //

    // 注册程序

    /**
     * @Title: register
     * @Description:
     * @param: [arr]
     * @return: void
     * @date: 2021/3/18 18:47
     * @version: 1.0
     */
    private void register(Member2[] arr) {
        boolean isFlag = true;
        String trueName = "";
        System.out.println("请填写个人注册信息：");
        while (isFlag) {
            System.out.print("用户名：");
            trueName = scanner.next();
            if (isRegisterArray(trueName, arr)) { // 已经注册
                System.out.println("该用户名已注册，请重新输入用户名");
            } else {
                isFlag = false;
            }
        }
        System.out.print("密码：");
        String truePassword = scanner.next();
        trueNo = (int) (Math.random() * 9000 + 1000);
        System.out.println("注册成功，请牢记您的会员卡号");
//        System.out.println(trueName + "\t\t\t" + truePassword + "\t" + trueNo);
        arr[index] = new Member2(trueNo, trueName, truePassword);
        System.out.println("用户名\t\t密码\t\t\t会员卡号");
        System.out.println(arr[index].info());
        index++;
        isRegister = true;
    }

    // 判断是否已经注册
    // 判断输入的账号密码是否在数组中？

    /**
     * @Title: isRegisterArray
     * @Description:
     * @param: [name, arr]
     * @return: boolean
     * @date: 2021/3/18 18:48
     * @version: 1.0
     */
    private boolean isRegisterArray(String name, Member2[] arr) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }

    // 用户登陆程序
    private void login(Member2[] arr) {
        int num = 3;
        while (num > 0) {
            System.out.print("请输入用户名：");
            String name = scanner.next();
            System.out.print("请输入密码：");
            String password = scanner.next();
            // 判断输入的用户名和密码是否存在于数组当中
            if (isInArray(name, password, arr)) {
                System.out.println("登录成功，欢迎您：" + name);
                isLogin = true;
                break;
            } else {
                if (num != 1) {
                    System.out.println("请重新输入！");
                    num--;
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，流程结束.");
                    break;
                }
            }
        }
    }

    // 判断输入的账号密码是否在数组中？
    private boolean isInArray(String name, String password, Member2[] arr) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name) && password.equals(arr[i].password)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }

    // 抽奖系统
    private void lottery() {
        int[] numArr = new int[5];
        boolean isLuckMember = false;
        System.out.println("生成的5个幸运会员ID是：");
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 9000 + 1000);
            System.out.print(numArr[i] + "\t");
            if (trueNo == numArr[i]) {
                isLuckMember = true;
            }
        }
        System.out.println();
        if (isLuckMember) {
            System.out.println("恭喜您成为本日幸运会员！");
        } else {
            System.out.println("抱歉！您不是今日幸运会员！");
        }
    }

    // 显示菜单
    private void viewMenu() {
        System.out.println("*****欢迎进入幸运抽奖系统*********");
        System.out.println("             1.注册              ");
        System.out.println("             2.登录              ");
        System.out.println("             3.抽奖              ");
        System.out.println("             4.显示所有会员       ");
        System.out.println("*********************************");
    }

    // 显示所有会员的信息
    private void viewMember(Member2[] arr) {
        System.out.println("用户名\t\t密码\t\t\t会员卡号");
        for (int i = 0; i < index; i++) {
            System.out.println(arr[i].info());
        }
    }
}


class Member2 {
    private int no;
    String name, password;

    Member2(int no, String name, String password) {
        this.no = no;
        this.name = name;
        this.password = password;
    }

    String info() {
        return name + "\t\t\t" + password + "\t\t\t" + no;
    }
}
