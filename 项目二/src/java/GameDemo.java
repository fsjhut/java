package java;

import java.util.Scanner;

/**
 * @author : SunHang
 * @className: GameDemo
 * @description:
 * @date: 2021/3/17 19:12
 * @version: 0.1
 * @since: 1.8
 */
public class GameDemo {
    // 判断是否登录
    private static boolean isLogin;
    // 判断是否注册
    private static boolean isRegister;
    // NUM：最大保存的用户的数量
    private static final int NUM = 10;
    // index记录当前数组保存到第几个数组位置
    private static int index = 0;
    // currentIndex记录当前账号的索引
    private static int currentIndex;
    // 保存电脑账号的索引
    private static int computerIndex;
    // 电脑的分数
    private static int cmpScore;
    // 用户的分数
    private static int userScore;
    // 对战次数
    private static int number;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 建立主程序对象，可以调用主程序的非静态方法
        GameDemo gameDemo = new GameDemo();
        // 创建User数组，保存用户信息
        User[] arr = new User[NUM];
        // 创建电脑数组，保存电脑的名字信息/刘备/孙权/曹操
        Computer[] cmp = new Computer[3];
        cmp[0] = new Computer("刘备");
        cmp[1] = new Computer("孙权");
        cmp[2] = new Computer("曹操");
        boolean isContinue = true;
        // 判断是否登录，若没有登录则提示用户登录
        while (isContinue) {
            if (isLogin) {
                // 若已登录，则显示游戏主界面
                gameDemo.view();
                System.out.println("请选择对方的角色(1:刘备 2:孙权 3:曹操)");
                computerIndex = scanner.nextInt() - 1;
                System.out.println(arr[currentIndex].name + "   VS   " + cmp[computerIndex].name);
                // 定义标记，是否退出
                boolean isFlag = true;
                // 循环显示对战
                while (isFlag) {
                    gameDemo.rule(arr, cmp);
                    System.out.print("是否开始下一轮(y/n):");
                    char temp = scanner.next().charAt(0);
                    if (temp != 'y') {
                        isFlag = false;
                        // 退出前显示结果信息
                        gameDemo.resultInfo(arr, cmp);
                    }
                }
                System.out.println("是否开始下一局比赛？(y/n)");
                char tmp = scanner.next().charAt(0);
                if (tmp != 'y') {
                    isContinue = false;
                    System.out.println("系统退出，欢迎下次使用！");
                }

            } else { // 没有登录则显示登录信息！
                System.out.println("请您先登录游戏！");
                gameDemo.login(arr);
            }
        }

    }

    // 注册
    private void register(User[] arr) {
        boolean isFlag = true;
        String name = "";
        System.out.println("请填写个人注册信息：");
        while (isFlag) {
            System.out.print("用户名：");
            name = scanner.next();
            if (isRegisterArray(name, arr)) { // 已经注册
                System.out.println("该用户名已注册，请重新输入用户名");
            } else {
                isFlag = false;
            }
        }
        System.out.print("密码：");
        String password = scanner.next();
        System.out.println("注册成功,请登录游戏！");
        arr[index] = new User(name, password);
        index++;
        isRegister = true;
    }

    // 判断是否登陆
    // 登录
    private void login(User[] arr) {
        // 判断是否注册过账户
        // 若没有注册过，则弹出注册信息
        if (isRegister) {
            // 登录信息的逻辑
            // num: 可设置最多重复登录次数
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
                        return;
                    }
                }
            }
        } else {
            // 弹出注册的信息
            System.out.println("请先注册游戏账号！");
            register(arr);
        }
    }

    // 判断登录信息是否在数组中
    private boolean isInArray(String name, String password, User[] arr) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name) && password.equals(arr[i].password)) {
                isFlag = true;
                currentIndex = i;
                break;
            }
        }
        return isFlag;
    }

    // 主程序界面
    private void view() {
        System.out.println("----------欢迎进入游戏世界-----------\n\n");
        System.out.println("         *****************");
        System.out.println("         **  猜拳，开始  **");
        System.out.println("         *****************");
    }

    // 游戏规则开始
    private void rule(User[] arr, Computer[] cmp) {
        System.out.print("请出拳：1.剪刀 2.石头 3.布(请输入相应数字)：");
        int temp = scanner.nextInt();
        System.out.println("你出拳：" + arr[currentIndex].showFist(temp));
        System.out.println(cmp[computerIndex].showFist());
        // 判断本次游戏的输赢？
        System.out.println(coreRules(arr, cmp));

    }

    // 单次输赢的判断规则
    public String coreRules(User[] arr, Computer[] cmp) {
        String info;
        number++;
        if (arr[currentIndex].number == cmp[computerIndex].random) {
            info = "结果：和局";
        } else if ((arr[currentIndex].number == 2) && (cmp[computerIndex].random == 1)) {
            info = "结果：恭喜，你赢了！";
            userScore++;
        } else if ((arr[currentIndex].number == 1) && (cmp[computerIndex].random == 3)) {
            info = "结果：恭喜，你赢了！";
            userScore++;
        } else if ((arr[currentIndex].number == 3) && (cmp[computerIndex].random == 2)) {
            info = "结果：恭喜，你赢了！";
            userScore++;
        } else {
            info = "你输了，继续加油！";
            cmpScore++;
        }
        return info;
    }

    // 对战结果
    public void resultInfo(User[] arr, Computer[] cmp) {
        System.out.println("------------------------------");
        System.out.println(arr[currentIndex].name + "   VS   " + cmp[computerIndex].name);
        System.out.println("对战次数：" + number + "\n");
        System.out.println("姓名\t\t" + "得分");
        System.out.println(arr[currentIndex].name + "\t\t" + userScore);
        System.out.println(cmp[computerIndex].name + "\t\t" + cmpScore + "\n");
        if (userScore >= cmpScore) {
            System.out.println("结果: 恭喜你获胜！");
            System.out.println("------------------------------");
        } else {
            System.out.println("请继续加油！");
            System.out.println("------------------------------");
        }
    }

    // 判断该用户名否注册过？
    private boolean isRegisterArray(String name, User[] arr) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }

}
