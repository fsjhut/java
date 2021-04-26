package java;
/**
 * @className: User
 * @description:   
 * @author SunHang
 * @createTime 2021/3/21 11:43
 */
// CompanyMgr.COMPANY_COUNTS: 代表三个公司,后期可以关联公司的个数
public class User {
    public int id; // 每个公司的员工ID唯一
    public String name; // 每个公司的员工名字唯一
    public String passWord;
    // 如果是普通用户则需要保存员工地址
    // 公司用户的地址全为默认值:北京
    public String aera = "北京";
    // 保存每个公司的员工人数
    public static int[] currStaffNum = new int[CompanyMgr.COMPANY_COUNTS];
    // 保存每个公司的用户数量
    public static int[] currUserNum = new int[CompanyMgr.COMPANY_COUNTS];
    // 0: 管理员 1:公司用户 2: 普通用户 Competence 权限

    public User(String name, String passWord ,int index) {
        this.id = currStaffNum[index];
        this.name = name;
        this.passWord = passWord;
        currStaffNum[index]++;
    }

    public User(String name, String passWord, String area, int index) {
        this.id = currUserNum[index];
        this.name = name;
        this.passWord = passWord;
        this.aera = area;
        currUserNum[index]++;
    }

    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", area='" + aera + '\'' +
                '}';
    }
}
