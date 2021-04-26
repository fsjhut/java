package service;

import bean.GoodsInfo;
import com.alibaba.fastjson.JSON;
import common.ServerResponse;
import imple.GoodsDAOImpl;
import view.Views;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @className: BuyServer
 * @description:
 * @createTime 2021/4/21 20:24
 */
public class BuyServer {
    // key---->商品id  value------>商品数量
    private static HashMap<Integer, GoodsInfo> goodsMap;
    private static HashMap<Integer, GoodsInfo> historyGoods;
    private static Scanner scanner = new Scanner(System.in);
    private static GoodsDAOImpl goodsDAO;
    private static PayServer payServer;

    static {
        goodsMap = new HashMap<>(10);
        historyGoods = new HashMap<>(12);
        goodsDAO = new GoodsDAOImpl();
        payServer = new PayServer();
    }

    public void buyCartMgr() {
        boolean isFlag = true;
        while (isFlag) {
            Views.buyCartMgrView();
            System.out.print("请输入您的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    addGoods();
                    break;
                case ("2"):
                    editGoodsNum();
                    break;
                case ("3"):
                    deleteGoods();
                    break;
                case ("4"):
                    showAllGoods();
                    break;
                case ("5"):
                    showHistoryGoods();
                    break;
                case ("6"):
                    return;
                case ("7"):
                    isFlag = false;
                    // 进入购买界面
                    payServer.payMgr(goodsMap);
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }

    private void showHistoryGoods() {
        System.out.println(JSON.toJSONString(historyGoods));
    }

    private boolean showAllGoods() {
        if (goodsMap == null || goodsMap.size() == 0) {
            System.out.println("购物车没有商品！");
            return false;
        }
        ArrayList<GoodsInfo> goodsArr = new ArrayList<>(10);
        for (Integer integer : goodsMap.keySet()) {
            GoodsInfo goodsInfo = goodsMap.get(integer);
            goodsArr.add(goodsInfo);
        }
        String string = JSON.toJSONString(goodsArr);
        System.out.println(string);
//        ArrayList<Integer> goodsArr = new ArrayList<>(10);
//        goodsArr.addAll(goodsMap.keySet());
//        ServerResponse serverResponse = goodsDAO.selectGoodsByIn(goodsArr);
//        if (serverResponse.getCode() == 400) {
//            System.out.println("购物车没有有效商品！");
//            return false;
//        }
//        String allGoods = JSON.toJSONString(serverResponse);
//        System.out.println(allGoods);
        return true;
    }

    private void deleteGoods() {
        if (!showAllGoods()) {
            return;
        }
        System.out.print("请输入您要删除商品的ID:");
        int goodsId = scanner.nextInt();
        // 判断goodsId是否在购物车中
        if (!goodsMap.containsKey(goodsId)) {
            System.out.println("你输入的商品ID不在购物车中！");
            return;
        }
        deleteGoods(goodsId);
    }

    private void deleteGoods(int goodsId) {
//        ServerResponse goodsInfo = goodsDAO.selectGoodsById(goodsId);
//        if (goodsInfo.getCode() == 200) {
//            System.out.println(goodsInfo);
//        }
        System.out.println(goodsMap.get(goodsId));
        System.out.println("确认删除该商品？(y/n)");
        if ("y".equals(scanner.next())) {
            goodsMap.remove(goodsId);
        }
    }

    private void editGoodsNum() {
        if (!showAllGoods()) {
            return;
        }
        System.out.print("请输入您要修改的商品的ID:");
        int goodsId = scanner.nextInt();
        // 判断goodsId是否在购物车中
        if (!goodsMap.containsKey(goodsId)) {
            System.out.println("你输入的商品ID不在购物车中！");
            return;
        }
        ServerResponse goodsInfo = goodsDAO.selectGoodsById(goodsId);
        if (goodsInfo.getCode() == 400) {
            System.out.println("该商品已售空！");
        }
        System.out.println(goodsInfo);
        System.out.print("请输入修改后的数量：");
        int updateNum = scanner.nextInt();
        if (updateNum == 0) {
            System.out.println("修改后商品数量为0，将从购物车删除该商品！");
            deleteGoods(goodsId);
        } else {
            // 判断商品库存数量与想购买的数量
            GoodsInfo goodsObj = (GoodsInfo) goodsInfo.getData();
            if (goodsObj.getNum() < updateNum) {
                System.out.println("该商品库存不足！");
            } else {
                goodsObj.setNum(updateNum);
                goodsMap.put(goodsId, goodsObj);
                System.out.println("修改购物车商品数量成功!");
            }
        }
    }

    private void addGoods() {
        ServerResponse serverResponse = goodsDAO.selectAllNormalGoods();
        String allGoods = JSON.toJSONString(serverResponse);
        System.out.println(allGoods);
        System.out.print("请输入将购买的商品ID:");
        int goodsId = scanner.nextInt();
        ServerResponse goodsInfo = goodsDAO.selectGoodsById(goodsId);
        if (goodsInfo.getCode() == 400) {
            System.out.println("该商品不存在！");
            return;
        }
        // 添加到游览过的列表中
        historyGoods.put(goodsId, (GoodsInfo) goodsInfo.getData());
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("该商品详情是:");
            System.out.println(goodsInfo);
            System.out.print("请输入你想购买的数量：");
            int buyNum = scanner.nextInt();
            if (buyNum == 0) {
                isFlag = false;
            } else {
                // 判断商品库存数量与想购买的数量
                GoodsInfo goodsObj = (GoodsInfo) goodsInfo.getData();
                if (goodsObj.getNum() < buyNum) {
                    System.out.println("该商品库存不足，请重新输入购买数量！");
                } else {
                    goodsObj.setNum(buyNum);
                    goodsMap.put(goodsId, goodsObj);
                    System.out.println("加入购物车成功!");
                    isFlag = false;
                }
            }
        }
    }
}
