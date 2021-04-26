package binaryTree.threadedbinarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: BinaryTree
 * @description:
 * @createTime 2021/4/5 19:16
 */
@Getter
@Setter
public class ThreadedBinaryTree {
    // root应该是固定的
    private HeroNode root;
    // 为了实现线索化,需要创建一个指向当前节点的前驱节点的指针
    // 在递归进行线索化时,pre总是保留前一个节点
    private HeroNode pre = null;


    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 编写对二叉树进行中序线索化的方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    public void threadedNodes(HeroNode node){
        // 如果node==null,则无法线索化
        if(node == null){
//            System.out.println("无法线索化");
            return;
        }
        // 中序线索化
        // 1. 先线索化左子树
        threadedNodes(node.getLeft());
        // 2. 线索化当前节点[有点难度]
        // 处理当前节点的前驱节点
        if(node.getLeft()==null){
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点左指针类型,指向前驱结点
            node.setLeftType(true);
        }
        // 处理后继节点
        if(pre!=null && pre.getRight()==null){
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(true);
        }
        // !!! 每处理一个节点后,让当前节点是下一个-->
        // 节点的前驱节点
        pre = node;

        // 3. 线索化右子树
        threadedNodes(node.getRight());


    }
    // 中序线索化二叉树的遍历
    public void infixOrder2(){
        HeroNode node = root;
        while(node != null){
            while(!node.isLeftType()){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.isRightType()){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }


    // 删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树,不能进行删除操作!");
        }
    }
    public void delNode2(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode2(no);
            }
        }else{
            System.out.println("空树,不能进行删除操作!");
        }
    }

    // 前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("为空,无法遍历");
        }
    }

    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("为空,无法遍历");
        }
    }

    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("为空,无法遍历");
        }
    }

}
