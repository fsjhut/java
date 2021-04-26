package binaryTree.normalbinarytree;
/**
 * @className: BinaryTreeDemo
 * @description:   
 * @author SunHang
 * @createTime 2021/4/5 19:12
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建二叉树的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        // 不同的遍历方式
        System.out.println("---------------");
        binaryTree.preOrder();
        System.out.println("---------------");
        binaryTree.infixOrder();
        System.out.println("---------------");
        binaryTree.postOrder();
        // 前序遍历查找
        HeroNode heroNode = binaryTree.getRoot().preOrderSearch(5);
        if(heroNode != null){
            System.out.println(heroNode.getName());
        }else{
            System.out.println("没有找到!");
        }
        // 后序遍历查找
        HeroNode heroNode2 = binaryTree.getRoot().postOrderSearch(5);
        if(heroNode2 != null){
            System.out.println(heroNode2.getName());
        }else{
            System.out.println("没有找到!");
        }
        // 删除节点
        System.out.println("删除节点--------");
        binaryTree.preOrder();
        binaryTree.delNode2(3);
        System.out.println("删除后的--------");
        binaryTree.preOrder();
    }

}
