package java;/**
 * @author : SunHang
 * @className: User
 * @description:
 * @date: 2021/3/17 19:11
 * @version: 0.1
 * @since: 1.8
 */
 public class User {
    String name;
    String password;
    // 游戏时，出的随机数字
    int number;

    // 构造器
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

     String showFist(int num){
        this.number = num;
        switch (number){
            case 1:
                return "剪刀";
            case 2:
                return "石头";
            case 3:
                return "布";
        }
        return "产生错误！";
    }

//    // 空参构造器
//    public User() {
//    }
}
