package service;

import bean.Order;
import bean.OrderInfo;
import imple.MemberDAOImpl;
import imple.OrderDAOImpl;
import imple.OrderInfoDAOImpl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: OrderServer
 * @description:
 * @createTime 2021/4/21 20:25
 */
public class OrderServer {
    private static OrderDAOImpl orderDAO;
    private static OrderInfoDAOImpl orderInfoDAO;
    private static Scanner scanner = new Scanner(System.in);

    static {
        orderDAO = new OrderDAOImpl();
        orderInfoDAO = new OrderInfoDAOImpl();
    }

    public void orderMgr() {
        System.out.println("1.根据商品Id查询 2.根据会员Id查询");
        String choice = scanner.next();
        if ("1".equals(choice)) {
            selectByGoodsId();
        } else if ("2".equals(choice)) {
            selectByMemberId();
        } else {
            System.out.println("输入错误，请重新输入!");
        }
    }

    private void selectByMemberId() {
        System.out.print("请输入会员Id：");
        int memberId = scanner.nextInt();
        List<Order> orders = null;
        try {
            orders = orderDAO.selectByUserId(memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (orders == null) {
            System.out.println("该会员没有订单信息！");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
            System.out.println("-------------------------------------------------------");
            int orderId = order.getId();
            List<OrderInfo> orderInfoList = selectOrderInfoByOrderId(orderId);
            if (orderInfoList != null) {
                for (OrderInfo orderInfo : orderInfoList) {
                    System.out.println(orderInfo);
                }
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    private List<OrderInfo> selectOrderInfoByOrderId(int orderId) {
        try {
            return orderInfoDAO.selectByOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void selectByGoodsId() {
        HashSet<Integer> allOrderId = new HashSet<>(10);
        System.out.print("请输入商品的Id: ");
        int goodsId = scanner.nextInt();
        try {
            List<OrderInfo> orderInfos = orderInfoDAO.selectByGoodsId(goodsId);
            for (OrderInfo orderInfo : orderInfos) {
                int orderId = orderInfo.getOrderId();
                allOrderId.add(orderId);
                System.out.println(orderInfo);
            }
            System.out.println("-------------------------------------------------------");
            List<Order> orders = orderDAO.selectByOrderIdIn(allOrderId);
            if (orders != null) {
                orders.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
