package com.javasm.test;

import com.alibaba.fastjson.JSONArray;
import com.javasm.jdbc.bean.UserInfo;
import com.javasm.jdbc.common.ServerResponse;
import com.javasm.jdbc.constants.CommonConstant;
import com.javasm.jdbc.dao.UserInfoDao;
import com.javasm.jdbc.dao.impl.UserInfoDaoImpl;
import com.javasm.jdbc.service.UserInfoService;
import com.javasm.jdbc.service.impl.UserInfoServiceImpl;
import lombok.Cleanup;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: DELL
 * @className: UserInfoTest
 * @description:
 * @date: 2021/4/17 16:39
 * @version:0.1
 * @since: 1.8
 */
public class UserInfoTest {

    //单元测试  junit

    @Test
    public void addUserTest() {
     UserInfoDao userInfoDao = new UserInfoDaoImpl();
        //用户信息
        for (int i = 0; i < 10; i++) {
            BigDecimal balance = new BigDecimal("8273");
            UserInfo userInfo = new UserInfo("张三" + i, 201, "a.jpg",
                    new Date(), balance, 4, "1234");
            System.out.println(userInfoDao.addUserInfo(userInfo));
        }

        System.out.println(Thread.currentThread());
        // service---> 业务逻辑处理层----> dao

//        userInfoDao.addUserInfo(userInfo);
//        UserInfoService userInfoService = new UserInfoServiceImpl();
////        String userImage = "C:\\Users\\DELL\\Pictures\\Saved Pictures\\a.jpg";
////        UserInfo userInfo = new UserInfo("王五", 20, userImage, new Date(), BigDecimal.valueOf(1000), 4, "1234");
////        ServerResponse response = userInfoService.addUser(userInfo);
////        System.out.println(response);
//
//        ServerResponse<UserInfo> response = userInfoService.selectOneUser(9);
//        System.out.println(response);
//
//        String jsonString = JSONArray.toJSONString(response);
//        System.out.println(jsonString);

    }

    @Test
    public void deleteTest() {
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
                    userInfo.setUsername(newName);
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

    public static void selectPageTest() {
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

    public static void main(String[] args) {
        selectByParams();
    }

    @Test
    public void selectAddressTest() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        userInfoDao.selectUserInfoAddress(9).forEach(System.out::println);

    }

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
