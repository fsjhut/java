package com.test.exec6;

import java.util.Arrays;

/**
 * @author SunHang
 * @className: Member
 * @description
 * @createTime 2021/3/20 9:52
 */
public class Member {
    int memberId;
    String name;
    int score;

    public Member(int memberId, String name, int score) {
        this.memberId = memberId;
        this.name = name;
        this.score = score;
    }

    public void confirmId(int id) {
        if (id < 999 || id  > 10000) {
            return;
        }
        discountInfo();
        draw();
    }

    private void discountInfo() {
        if (score < 100) {
            System.out.println("恭喜您拥有9折优惠!");
        } else if (score < 1000) {
            System.out.println("恭喜您拥有7折优惠!");
        } else {
            System.out.println("恭喜您拥有6折优惠!");
        }
    }

    private void draw() {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 9000 + 1000);
        }
        System.out.println("幸运会员ID: " + Arrays.toString(arr));
        for (int id : arr) {
            if (memberId == id) {
                System.out.println("恭喜您已中奖!");
                return;
            } else {
                System.out.println("祝您下次好运!");
                return;
            }
        }
    }
}
