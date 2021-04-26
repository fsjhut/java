package binaryTree.threadedbinarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: HeroNode
 * @description:
 * @createTime 2021/4/5 19:18
 */
@Getter
@Setter
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    // 说明:
    // 1. 如果leftType == 0 表示指向的是左子树 如果是1 指向前驱结点
    // 2. 如果rightType == false 指向右子树,true: 后继节点
    private boolean leftType = false;
    private boolean rightType = false;

    // 没有空参构造器
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [" +
                "no=" + no +
                ", name='" + name + '\'' +
                ']';
    }

    public void delNode(int no) {
        /**
         * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
         2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
         3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
         4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
         2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
         */
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左递归进行删除
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }
    public void delNode2(int no) {
        /**
         * 删除的规则: 如果非叶子节点A只有一个子节点B,则用该节点代替A
         * 如果节点A有左子节点B和右子节点C ,则用B来代替A
         */
        if (this.left != null && this.left.no == no) {
            if(this.left.left==null){
                if(this.left.right==null){
                    this.left = null;
                    return;
                }else{
                    this.left = this.left.right;
                    return;
                }
            }else if(this.left.right == null){
                this.left = this.left.left;
                return;
            }else {
                HeroNode tempNode = this.left.right;
                this.left = this.left.left;
                this.left.right = tempNode;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            if(this.right.left==null){
                if(this.right.right==null){
                    this.right = null;
                    return;
                }else{
                    this.right = this.right.right;
                    return;
                }
            }else if(this.right.right == null){
                this.left = this.right.left;
                return;
            }else {
                HeroNode tempNode = this.right.right;
                this.right = this.right.left;
                this.right.right = tempNode;
                return;
            }
        }
        // 向左递归进行删除
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }

    // 编写前序遍历的方法
    // 先输出父节点--> 左子节点--->右子节点
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    // 线索化后的中序遍历
    // 不用递归,直接利用链表遍历

    // 后序遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找
    // 找到返回该node节点,没有则返回null
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历出-----");
        HeroNode resNode = null;
        if(this.no == no){
            return this;
        }
        if(this.left!=null){
            resNode = this.left.preOrderSearch(no);
        }
        // 优化下,如果在左子树节点找到,则不遍历右子节点树
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序遍历
    public HeroNode infixOrderSearch(int no){
        System.out.println("中序遍历----");
        HeroNode resultNode = null;
        if(this.left!=null){
            resultNode = this.left.infixOrderSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;
    }

    // 后序遍历
    public HeroNode postOrderSearch(int no){
        System.out.println("中序遍历----");
        HeroNode resultNode = null;
        if(this.left!=null){
            resultNode = this.left.infixOrderSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        if(this.right != null){
            resultNode = this.right.infixOrderSearch(no);
        }
        if(resultNode!=null){
            return resultNode;
        }
        if(this.no == no){
            return this;
        }
        return null;
    }
}
