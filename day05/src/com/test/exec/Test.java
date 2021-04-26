package com.test.exec;/**
 *@className: Test
 *@description 
 *@author SunHang
 *@createTime 2021/3/18 21:28
 */
public class Test {
    public static void main(String[] args) {
        Car car = new Car(2,"蓝色");
        car.run();
    }
}

class Car{
    int wheelNum = 4;
    String colour = "红色";
    RepairCar repairFactory = new RepairCar();


    public Car(int wheelNum, String colour) {
        this.wheelNum = wheelNum;
        this.colour = colour;
    }

    public Car() {
    }
    public void run(){
        if(this.wheelNum == 4){
            System.out.println("车正常，可以跑！");
        }else{
            System.out.println("车辆有故障，请修理！");
            repairFactory.repair(this);
        }
    }
}

class RepairCar{
    String repairName = "爱心修车厂";
    String repairAddress = "心里";
    String phoneNum = "888888888888";

    public RepairCar(String repairName, String repairAddress, String phoneNum) {
        this.repairName = repairName;
        this.repairAddress = repairAddress;
        this.phoneNum = phoneNum;
    }

    public RepairCar() {

    }

    public void repair(Car car){
        car.wheelNum = 4;
        System.out.println("已经修理完毕，请重新跑！");
    }
}