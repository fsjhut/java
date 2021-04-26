package service;

import bean.GoodsInfo;
import bean.MemberInfo;
import bean.Order;
import bean.OrderInfo;
import common.ServerResponse;
import imple.GoodsDAOImpl;
import imple.MemberDAOImpl;
import imple.OrderDAOImpl;
import imple.OrderInfoDAOImpl;
import util.DBHelper;
import util.Utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author SunHang
 * @className: PayServer
 * @description:
 * @createTime 2021/4/21 22:28
 */
public class PayServer {
    private static Scanner scanner;
    private static MemberDAOImpl memberDAO;
    private static GoodsDAOImpl goodsDAO;

    static {
        scanner = new Scanner(System.in);
        memberDAO = new MemberDAOImpl();
        goodsDAO = new GoodsDAOImpl();
    }

    public void payMgr(HashMap<Integer, GoodsInfo> goodsMap) {
        // 计算购物车所有商品的价格
        // 开启事务
        Connection connection = DBHelper.getConn();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("是否使用会员卡支付？(y/n)");
        String str = scanner.next();
        try {
            if ("y".equals(str)) {
                if (memberPay(connection, goodsMap)) {
                    connection.commit();
                    // 若购买后商品数量为0，则将状态置为2
                    updateGoodsInfo(goodsMap);
                    goodsMap.clear();
//                    connection.setAutoCommit(true);
                    System.out.println("购买成功！");
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } else {
                System.out.println("欢迎使用现金支付！");
                if (cashPay(connection, goodsMap)) {
                    connection.commit();
                    updateGoodsInfo(goodsMap);
                    goodsMap.clear();
//                    connection.setAutoCommit(true);
                    System.out.println("购买成功");
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGoodsInfo(HashMap<Integer, GoodsInfo> goodsMap) {
        Set<Integer> goodsIdSet = goodsMap.keySet();
        for (Integer goodsId : goodsIdSet) {
            GoodsInfo goodsInfo = goodsMap.get(goodsId);
            if (goodsInfo.getNum() == 0) {
                goodsInfo.setStatus((byte) 2);
                goodsDAO.updateGoodsById(goodsInfo);
            }
        }
    }

    // 计算购物车所有商品的价格
    private float calculatePrice(Connection connection, HashMap<Integer, GoodsInfo> goodsMap) {
        GoodsDAOImpl goodsDAO = new GoodsDAOImpl();
        return goodsDAO.selectAllPrice(connection, goodsMap);
    }

    private boolean cashPay(Connection connection, HashMap<Integer, GoodsInfo> goodsMap) {
        BigDecimal payMoney = new BigDecimal(calculatePrice(connection, goodsMap));
        System.out.println("你需要支付的金额是：" + payMoney);
        // 生成非会员的临时ID
        int memberId = Utils.getId();
        int orderId = generateOrder(connection, memberId, payMoney, 1);
        if (orderId == 0) {
            System.out.println("订单生成失败！");
            return false;
        }
        return generateOrderInfo(connection, orderId, goodsMap);
    }

    private boolean memberPay(Connection connection, HashMap<Integer, GoodsInfo> goodsMap) {
        System.out.println("欢迎您使用会员支付！");
        int num = 3;
        while (true) {
            if (goodsMap == null || goodsMap.size() == 0) {
                System.out.println("购物车没有商品！");
                return false;
            }
            System.out.print("请输入你的会员Id：");
            int memberId = scanner.nextInt();
            System.out.print("请输入你的密码：");
            String memberPass = scanner.next();
            ServerResponse serverResponse = verifyMemberInfo(memberId, memberPass);
            if (serverResponse.getCode() == 200) {
                MemberInfo memberInfo = (MemberInfo) serverResponse.getData();
                System.out.println(memberInfo.getName() + "，欢迎您！");
                BigDecimal payMoney = new BigDecimal(calculatePrice(connection, goodsMap));
                System.out.println("你需要支付的金额是：" + payMoney);
                BigDecimal balance = memberInfo.getBalance();
//                payMoney.floatValue()
                if (balance.compareTo(payMoney) >= 0) {
                    int opt = memberDAO.addMoney(connection, memberId, payMoney.negate());
                    if (opt == 0) {
                        System.out.println("支付失败");
                        return false;
                    }
                    System.out.println("支付成功，正在生成订单...");
                    // 生成订单表,并获取订单的ID
                    int orderId = generateOrder(connection, memberId, payMoney, 0);
                    if (orderId == 0) {
                        System.out.println("订单生成失败！");
                        return false;
                    }
                    // 生成订单详情表，需要获取订单表的ID
                    return generateOrderInfo(connection, orderId, goodsMap);
                } else {
                    System.out.println("余额不足，请充值！");
                    return false;
                }

            } else {
                System.out.println("你输入的用户名或密码错误！");
                if (num != 1) {
                    num--;
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，程序结束!");
                    return false;
                }
            }
        }
    }

    private boolean generateOrderInfo(Connection connection, int orderId, HashMap<Integer, GoodsInfo> goodsMap) {
        // 利用hashMap中的商品Id和商品数量循环生成订单详情表
        Iterator<Integer> iterator = goodsMap.keySet().iterator();
        OrderInfoDAOImpl orderInfoDAO = new OrderInfoDAOImpl();
        while (iterator.hasNext()) {
            Integer goodsId = iterator.next();
            int num = goodsMap.get(goodsId).getNum();
            OrderInfo orderInfo = new OrderInfo(orderId, goodsId, num);
            int isFlag = orderInfoDAO.addOrderInfo(connection, orderInfo);
            if (isFlag == 0) {
                System.out.println("生成商品ID：" + goodsId + "的订单失败！");
                return false;
            }
            // 订单生成成功后，将goodsInfo中的库存减少
            if (reduceGoodsNum(connection, goodsId, goodsMap.get(goodsId).getNum())) {
                System.out.println("生成商品ID:" + goodsId + "的订单成功！");
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean reduceGoodsNum(Connection connection, Integer goodsId, Integer goodsNum) {
        ServerResponse serverResponse = goodsDAO.selectGoodsById(goodsId);
        if (serverResponse.getCode() == 200) {
            GoodsInfo goodsInfo = (GoodsInfo) serverResponse.getData();
            if (goodsInfo.getNum() >= goodsNum) {
                goodsInfo.setNum(goodsInfo.getNum() - goodsNum);
                // 将修改传回数据库
                ServerResponse serverResponse1 = goodsDAO.updateGoodsById(connection, goodsInfo);
                if (serverResponse1.getCode() == 200) {
                    return true;
                } else {
                    System.out.println("购买失败！");
                }
            } else {
                System.out.println("库存数量不足！");
            }
        } else {
            System.out.println("购物车存在异常的商品！");
        }
        return false;
    }

    private int generateOrder(Connection connection, int memberId, BigDecimal totalMoney, int payType) {
        Order order = new Order(memberId, totalMoney, (byte) payType);
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        try {
            return (int) orderDAO.addOrder(connection, order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private ServerResponse verifyMemberInfo(int memberId, String memberPass) {
        MemberDAOImpl memberDAO = new MemberDAOImpl();
        String encodeStr = Utils.encodeStr(memberPass);
        return memberDAO.selectMemberByIdAndPass(memberId, encodeStr);
    }
}