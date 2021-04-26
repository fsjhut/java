package com.test2.exec5;

import java.util.Objects;

/**
 * @className: PatternTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 19:18
 */
public class PatternTest {
    public static void main(String[] args) {
        Pattern[] patterns = new Pattern[3];
        patterns[0] = new Liner(10,20);
        patterns[1] = new Rectangle(10,4);
        patterns[2] = new Circle(3);
        PatternTest.draw(patterns);
    }

    public static void draw(Pattern[] patterns){
        for(int i = 0; i<patterns.length;i++){
            Objects.requireNonNull(patterns[i]);
            patterns[i].draw();
        }
    }
}
