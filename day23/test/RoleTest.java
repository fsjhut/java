import jdbc.bean.UserInfo;
import jdbc.constants.CommonConstant;
import jdbc.dao.impl.UserInfoDao;
import jdbc.dao.impl.impl.UserInfoDaoImpl;
import lombok.Cleanup;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @className: RoleTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 22:29
 */
public class RoleTest {
    @Test
    public void deleteTest(){
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        int[] idArray = {17, 16, 21};
        System.out.println(userInfoDao.deleteUserByIn(idArray));
//        for (int id : idArray) {
//            System.out.println(userInfoDao.deleteUserInfoById(id));
//        }
    }
    @Test
    public void selectOneTest() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();//java.sql.Date
        UserInfo userInfo = userInfoDao.selectUserById(9);
        System.out.println(userInfo);

//        Date date = new Date();
//        Instant instant = date.toInstant();
//        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
//        LocalDate localDate = LocalDate.from(dateTime);
//        System.out.println(localDate);
//
//        LocalDate.from(new Date().toInstant().atZone(ZoneId.systemDefault()));
    }
    @Test
    private static void updateTest() {

        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("修改用户id:");
        int uid = input.nextInt();
        UserInfo userInfo = userInfoDao.selectUserById(uid);


        System.out.println("用户信息如下:" + userInfo);
        System.out.println("请选择要修改的字段: 1.username 2. image 3. age 4. birthday 5. balance 6. rid");
        String choice = input.next();//1,2,3
        String[] array = choice.split(",");
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.println("请录入新的username:");
                    String newName = input.next();
                    userInfo.setName(newName);
                    break;
                case 2:
                    System.out.println("请录入新的image:");
                    String newImage = input.next();
                    userInfo.setImage(newImage);
                    break;
                case 3:
                    System.out.println("请录入新的age:");
                    int newAge = input.nextInt();
                    userInfo.setAge(newAge);
                    break;
                case 4:
                    System.out.println("请录入新的birthday:");
                    String newBirthday = input.next();
                    userInfo.setBirthday(new Date());
                    break;
                case 5:
                    System.out.println("请录入新的balance:");
                    BigDecimal newBalance = input.nextBigDecimal();
                    userInfo.setBalance(newBalance);
                    break;
                case 6:
                    System.out.println("请录入新的rid:");
                    userInfo.setRid(input.nextInt());
                    break;
            }
        }

        System.out.println("修改之后的用户信息:" + userInfo);
        System.out.println(userInfoDao.updateUserInfoById(userInfo));
    }
    @Test
    public void selectAllTest() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        userInfoDao.selectAllUserInfo().forEach(System.out::println);
    }
    @Test
    public  void selectPageTest() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        int count = userInfoDao.selectCount();
        int result = count / CommonConstant.SIZE;
        int totalPage = (count % CommonConstant.SIZE == 0) ? (result) : (result + 1);
        for (int i = 1; i <= totalPage; i++) {
            System.out.print(i + ", ");
        }
        System.out.println("请选择要查看的页数:");
        @Cleanup
        Scanner input = new Scanner(System.in);
        int page = input.nextInt();


        userInfoDao.selectUserInfoByPage(page).forEach(System.out::println);

//        userInfoDao.selectAllUserInfo().forEach(System.out::println);
    }
    @Test
    public void selectAddressTest() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        userInfoDao.selectUserIfoAddress(9).forEach(System.out::println);
    }
    @Test
    private static void selectByParams() {
        Scanner input = new Scanner(System.in);
        Map<String, Object> params = new HashMap<>(16);

        System.out.println("请选择要查询的字段: 1.username 2. image 3. age  5. balance 6. rid");
        String choice = input.next();//1,2,3
        String[] array = choice.split(",");
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.println("请录入username:");
                    params.put("username", input.next());
                    break;
                case 2:
                    System.out.println("请录入image:");
                    params.put("image", input.next());
                    break;
                case 3:
                    System.out.println("请录入age:");
                    params.put("age", input.nextInt());
                    break;
                case 5:
                    System.out.println("请录入balance:");
                    params.put("balance", input.nextBigDecimal());
                    break;
                case 6:
                    System.out.println("请录入rid:");
                    params.put("rid", input.nextInt());
                    break;
            }
        }
        //确定查询的字段和字段对应的数据
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        System.out.println(params);
        userInfoDao.selectByParams(params).forEach(System.out::println);
    }


}
