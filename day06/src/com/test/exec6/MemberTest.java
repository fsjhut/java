package com.test.exec6;
/**
 *@className: MemberTest
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:52
 */

import java.util.Scanner;

/**
 *接收控制台用户输入会员号、会员姓名、积分，创建一个会员对象，
 *验证会员号是否合法，不合法提示退出，合法保存会员数据，
 *同时显示当前用户的(积分)折扣信息，
 *最后进行抽奖，提示是否是(会员号)幸运用户。
 */
public class MemberTest {
    public static void main(String[] args) {
        Member member = new Member(1022,"sun",1000);
        member.confirmId(member.memberId);
    }
}
