package com.test2.exec4;
/**
 * @className: MonsterTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 19:05
 */
public class MonsterTest {
    public static void main(String[] args) {
        Monster[] monsters = new Monster[3];
        monsters[0] = new Goblins("哥布林1",100,50,30,100);
        monsters[1] = new CatDemons("猫妖",100,50,30);
        monsters[2] = new Goblins("哥布林2",10,500,70,300);
        MonsterTest.Test(monsters);

    }

    public static void Test(Monster[] monsters){
        for (Monster monster : monsters) {
            monster.attack();
            System.out.println(monster);
            monster.move();
//            if(monster instanceof CatDemons){
//                CatDemons catDemons = (CatDemons)monster;
//                catDemons.attack();
//                catDemons.move();
//            }
//            if(monster instanceof Goblins){
//                Goblins goblins = (Goblins)monster;
//                goblins.attack();
//                goblins.move();
//            }
        }
    }
}
