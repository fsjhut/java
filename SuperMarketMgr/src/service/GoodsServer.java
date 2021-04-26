package service;

import bean.GoodsInfo;
import common.ServerResponse;
import imple.GoodsDAOImpl;
import imple.GoodsTypeDAOImpl;
import util.Utils;
import view.Views;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: GoodsServer
 * @description:
 * @createTime 2021/4/21 20:24
 */
public class GoodsServer {
    private static GoodsDAOImpl goodsDAO;
    private static GoodsTypeDAOImpl goodsTypeDAO;
    private static Scanner scanner = new Scanner(System.in);

    static {
        goodsDAO = new GoodsDAOImpl();
        goodsTypeDAO = new GoodsTypeDAOImpl();
    }

    public void goodsMgr() {
        while (true) {
            Views.goodsMgrView();
            System.out.print("请输入您的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    addGoods();
                    break;
                case ("2"):
                    editGoods();
                    break;
                case ("3"):
                    selectGoods();
                    break;
                case ("4"):
                    deleteGoods();
                    break;
                case ("5"):
                    return;
                case ("6"):
                    Utils.exitSystem();
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }

    private void addGoods() {
        System.out.print("请输入你要添加的商品名字：");
        String goodsName = scanner.next();
        System.out.print("请输入你要添加的商品的类型：");
        int type = scanner.nextInt();
        type = typeVerify(type);
        System.out.print("请输入你要添加的商品数量：");
        int num = scanner.nextInt();
        System.out.print("请输入你要添加的商品价格：");
        float price = scanner.nextFloat();
        System.out.print("请输入你要添加的商品状态：");
        int status = scanner.nextInt();
        System.out.print("请输入你要添加的商品折扣(1-10)：");
        int discount = scanner.nextInt();
        GoodsInfo goods = new GoodsInfo(goodsName, type, num, price, (byte) status, discount);
        ServerResponse serverResponse1 = goodsDAO.addGoods(goods);
        if (serverResponse1.getCode() == 200) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }

    private void deleteGoods() {
        // 将商品类型置为3--->已删除商品
        System.out.print("请输入要删除的商品的ID:");
        int goodsId = scanner.nextInt();
        ServerResponse serverResponse = goodsDAO.selectGoodsById(goodsId);
        if (serverResponse.getCode() == 400) {
            System.out.println("该Id对应的商品不存在！");
            return;
        }
        GoodsInfo goodsInfo = (GoodsInfo) serverResponse.getData();
        goodsInfo.setStatus((byte) 3);
        ServerResponse serverResponse1 = goodsDAO.updateGoodsById(goodsInfo);
        if (serverResponse1.getCode() == 200) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败！");
        }
    }

    private void selectGoods() {
        System.out.println("请你输入查询类型(1：单个商品 2：全部商品 3：在售商品 4：已下架商品)");
        ServerResponse serverResponse = null;
        String choice = scanner.next();
        switch (choice) {
            case ("1"):
                System.out.print("请输入你要查询的商品的ID:");
                int goodsId = scanner.nextInt();
                serverResponse = goodsDAO.selectGoodsById(goodsId);
                break;
            case ("2"):
                serverResponse = goodsDAO.selectAllGoods();
                break;
            case ("3"):
                serverResponse = goodsDAO.selectAllNormalGoods();
                break;
            case ("4"):
                serverResponse = goodsDAO.selectOffShelves();
                break;
            default:
                System.out.println("输入错误！");
        }
        Objects.requireNonNull(serverResponse);
        if (serverResponse.getCode() == 200) {
            System.out.println("查询成功");
            System.out.println(serverResponse);
        } else {
            System.out.println("查询失败！");
        }
    }

    private void editGoods() {
        System.out.println("需要修改的商品Id:");
        int id = scanner.nextInt();
        ServerResponse serverResponse1 = goodsDAO.selectGoodsById(id);
        if (serverResponse1.getCode() == 400) {
            System.out.println("该ID的商品不存在");
            return;
        }
        GoodsInfo goodsInfo = (GoodsInfo) serverResponse1.getData();
        System.out.println("商品类型信息如下:" + goodsInfo);
        System.out.println("请选择要修改的字段: 1.name 2.type 3.num 4.price 5.status 6.discount");
        String choice = scanner.next();//1,2,3
        String[] array = choice.split(",");
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.print("请录入修改后的商品名字：");
                    String newName = scanner.next();
                    goodsInfo.setName(newName);
                    break;
                case 2:
                    System.out.println("请录入新的类型:");
                    int newType = scanner.nextInt();
                    // 判断类型是否存在
                    newType = typeVerify(newType);
                    goodsInfo.setType(newType);
                    break;
                case 3:
                    System.out.println("请录入新的商品数量:");
                    int newNum = scanner.nextInt();
                    if (newNum == 0) {
                        goodsInfo.setNum(0);
                        // 置为下架
                        goodsInfo.setStatus((byte) 2);
                    } else {
                        goodsInfo.setNum(newNum);
                    }
                    break;
                case 4:
                    System.out.println("请录入新的价格:");
                    goodsInfo.setPrice(scanner.nextFloat());
                    break;
                case 5:
                    System.out.println("请录入新的status(1,2,3):");
                    byte status = scanner.nextByte();
                    if (status == 1 && goodsInfo.getNum() == 0) {
                        System.out.println("商品数量为0，无法上架, 请先修改商品数量！");
                        return;
                    }
                    goodsInfo.setStatus(status);
                    break;
                case 6:
                    System.out.println("请录入新的折扣(1.0-10.0):");
                    goodsInfo.setDiscount(scanner.nextFloat());
                    break;
            }
        }
        System.out.println("修改之后的商品类型信息:" + goodsInfo);
        ServerResponse serverResponse = goodsDAO.updateGoodsById(goodsInfo);
        if (serverResponse.getCode() == 200) {
            System.out.println("修改成功！");
            System.out.println(serverResponse);
        } else {
            System.out.println("修改失败！");
        }
    }

    private int typeVerify(int type) {
        while (true) {
            ServerResponse serverResponse = goodsTypeDAO.selectTypeById(type);
            if (serverResponse.getCode() == 400) {
                System.out.println("该商品类型不存在！");
                System.out.print("请录入新的商品类型Id:");
                type = scanner.nextInt();
            } else {
                return type;
            }
        }
    }
}
