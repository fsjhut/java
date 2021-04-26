package service;

import bean.GoodsType;
import com.alibaba.fastjson.JSON;
import common.ServerResponse;
import imple.GoodsDAOImpl;
import imple.GoodsTypeDAOImpl;
import util.Utils;
import view.Views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: GoodsTypeServer
 * @description:
 * @createTime 2021/4/21 20:24
 */
public class GoodsTypeServer {
    private static GoodsTypeDAOImpl goodsTypeDAO;
    private static GoodsDAOImpl goodsDAO;
    private static Scanner scanner = new Scanner(System.in);

    static {
        goodsTypeDAO = new GoodsTypeDAOImpl();
        goodsDAO = new GoodsDAOImpl();
    }

    public void goodsTypeMgr() {
        while (true) {
            Views.goodsTypeMgrView();
            System.out.print("请输入您的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    addGoodsType();
                    break;
                case ("2"):
                    editGoodsType();
                    break;
                case ("3"):
                    selectGoodsType();
                    break;
                case ("4"):
                    deleteGoodsType();
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

    private void deleteGoodsType() {
        System.out.print("请输入要删除的商品类型ID：");
        int goodsTypeId = scanner.nextInt();
        if (isFatherVerify(goodsTypeId)) {
            System.out.println("该商品类型存在子类型，无法删除！");
            return;
        }
        if (isExistGoods(goodsTypeId)) {
            System.out.println("该类型存在在售商品，无法删除！");
            return;
        }
        ServerResponse serverResponse = goodsTypeDAO.deleteTypeById(goodsTypeId);
        if (serverResponse.getCode() == 200) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败！");
        }

    }

    private boolean isExistGoods(int goodsTypeId) {
        ServerResponse serverResponse = goodsDAO.selectGoodsByType(goodsTypeId);
        return serverResponse.getCode() != 400;
    }

    private void selectGoodsType() {
        System.out.println("1：查询单个类型信息 2：查询所有类型信息");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("请输入你要查询的商品类型ID：");
            int goodsTypeId = scanner.nextInt();
//            ServerResponse serverResponse = goodsTypeDAO.selectTypeById(goodsTypeId);
//            if (serverResponse.getCode() == 400) {
//                System.out.println("没有该ID的商品类型！");
//                return;
//            }
//            GoodsType goodsType = (GoodsType) serverResponse.getData();
//            System.out.println(goodsType);
//            HashMap<String, Object> map = new HashMap<>();
//            List<Object> list = new ArrayList<>();
//            list = goodsTypeDAO.selectTypeByIsFather(goodsTypeId, map, list);
//            HashMap<String, Object> map2 = (HashMap<String, Object>) list.get(0);
//            String jsonString = JSON.toJSONString(map2);
//            System.out.println("该类型包含的子类如下：");
//            System.out.println(jsonString);
            List<GoodsType> typeList = goodsTypeDAO.setAllGoodsType();
            List<GoodsType> types = null;
            for (GoodsType type : typeList) {
                if (type.getId()==goodsTypeId){
                    types = type.getChildList();
                    break;
                }
            }
            if(types==null){
                System.out.println("没有该Id对应的类型");
                return;
            }
            String typeString = JSON.toJSONString(types);
            System.out.println("该类型包含的子类如下：");
            System.out.println(typeString);
        } else if (choice == 2) {
//            ServerResponse serverResponse = goodsTypeDAO.showAllType();
//            if (serverResponse.getCode() == 200) {
//                System.out.println("查询成功，所有类型如下：");
//                String jsonString = JSON.toJSONString(serverResponse);
//                System.out.println(jsonString);
//            } else {
//                System.out.println("查询所有类型失败");
//            }
            List<GoodsType> typeList = goodsTypeDAO.setAllGoodsType();
            HashMap<String, List<GoodsType>> map = new HashMap<>();
            for (GoodsType type : typeList) {
                if (type.getPid()==0){
                    map.put(type.getTypeName(),type.getChildList());
                }
            }
            if(map.size()==0){
                System.out.println("没有商品类型！");
                return;
            }
            String allTypes = JSON.toJSONString(map);
            System.out.println(allTypes);
        } else {
            System.out.println("输入错误！");
        }
    }

    private void editGoodsType() {

        System.out.print("修改商品类型的Id:");
        int id = scanner.nextInt();
        ServerResponse serverResponse1 = goodsTypeDAO.selectTypeById(id);
        if (serverResponse1.getCode() == 400) {
            System.out.println("该ID的商品类型不存在");
            return;
        }
        GoodsType goodsType = (GoodsType) serverResponse1.getData();
        System.out.println("商品类型信息如下:" + goodsType);
        System.out.println("请选择要修改的字段: 1.pid 2. typeName 3. isFather 4. status");
        String choice = scanner.next();//1,2,3
        String[] array = choice.split(",");
        showAllTypes();
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.print("请录入修改后的父类Id：");
                    int pid = scanner.nextInt();
                    if (pid != 0) {
                        pid = pidVerify(pid);
                    }
                    goodsType.setPid(pid);
                    break;
                case 2:
                    System.out.print("请录入新的类型名称:");
                    String newTypeName = scanner.next();
                    goodsType.setTypeName(newTypeName);
                    break;
                case 3:
                    System.out.print("请录入新的isFather:");
                    byte newIsFather = scanner.nextByte();
                    if (newIsFather == 0) {
                        if (!isFatherVerify(goodsType.getId())) {
                            goodsType.setIsFather((byte) 0);
                        } else {
                            System.out.println("该类型存在子类型，无法修改！");
                        }
                    }
                    goodsType.setIsFather((byte) 1);
                    break;
                case 4:
                    System.out.print("请录入新的status:");
                    goodsType.setStatus(scanner.nextByte());
                    break;
            }
        }
        System.out.println("修改之后的商品类型信息:" + goodsType);
        ServerResponse serverResponse = goodsTypeDAO.updateTypeById(goodsType);
        if (serverResponse.getCode() == 200) {
            System.out.println("修改成功！");
            System.out.println(serverResponse);
        } else {
            System.out.println("修改失败！");
        }
    }

    private boolean isFatherVerify(int id) {
        ServerResponse serverResponse = goodsTypeDAO.selectIsFatherById(id);
        if (serverResponse.getCode() == 400) {
            return false;
        } else {
            return true;
        }
    }

    private void addGoodsType() {
        showAllTypes();
        System.out.print("请输入你要添加的商品类型名字：");
        String goodsTypeName = scanner.next();
        System.out.print("请输入你要添加的商品的父类ID：");
        int pid = scanner.nextInt();
        if (pid != 0) {
            pid = pidVerify(pid);
        }
        System.out.print("该商品类型是否是父类型(1/0):");
        int isFather = scanner.nextInt();
        System.out.print("该商品类型的状态(1/2)：");
        int status = scanner.nextInt();
        GoodsType goodsType = new GoodsType(pid, goodsTypeName, (byte) isFather, (byte) status);
        ServerResponse serverResponse1 = goodsTypeDAO.addType(goodsType);
        if (serverResponse1.getCode() == 200) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }

    private void showAllTypes() {
        ServerResponse serverResponse = goodsTypeDAO.showAllType();
        String allTypes = JSON.toJSONString(serverResponse);
        System.out.println(allTypes);
    }

    private int pidVerify(int pid) {
        while (true) {
            ServerResponse serverResponse = goodsTypeDAO.selectTypeById(pid);
            if (serverResponse.getCode() == 400) {
                System.out.println("该父类ID不存在！");
                System.out.print("请录入新的父类Id:");
                pid = scanner.nextInt();
            } else {
                return pid;
            }
        }
    }
}
