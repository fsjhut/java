package java;/**
 * @author : SunHang
 * @className: Computer
 * @description:
 * @date: 2021/3/17 19:11
 * @version: 0.1
 * @since: 1.8
 */

// 电脑有三个用户
 public class Computer {
    String name;
    int random;

    // 构造器
    public Computer(String name) {
        this.name = name;
    }

    public int createRandom(){
        random = (int)(Math.random()*3+1);
        return random;
    }
    public String showFist(){
        // 调用产生的随机数方法
        switch (createRandom()){
            case 1:
                return name + "出拳："+" 剪刀";
            case 2:
                return name + "出拳："+" 石头";
            case 3:
                return name + "出拳："+" 布";
        }
        return "产生错误！";
    }
}
