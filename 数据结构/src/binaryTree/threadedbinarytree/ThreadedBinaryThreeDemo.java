package binaryTree.threadedbinarytree;

import javax.swing.*;

/**
 * @author SunHang
 * @className: ThreadedBinaryThreeDemo
 * @description:
 * @createTime 2021/4/5 21:56
 */
public class ThreadedBinaryThreeDemo {
    public static void main(String[] args) {
        // 测试中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "sun");
        HeroNode node5 = new HeroNode(10, "hang");
        HeroNode node6 = new HeroNode(14, "king");
        // 递归创建二叉树,先手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        // 测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        // 测试 以10号节点为例
        HeroNode left = node2.getLeft();
        HeroNode right = node2.getRight();
        System.out.println("十号节点的前驱节点: " + left.getNo());
        System.out.println("十号节点的后继节点: " + right.getNo());
        System.out.println("----------------------------");
        threadedBinaryTree.infixOrder2();
    }
}


// 创建HeroNode节点
