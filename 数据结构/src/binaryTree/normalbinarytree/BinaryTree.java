package binaryTree.normalbinarytree;

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
public class BinaryTree {
    // root应该是固定的
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
