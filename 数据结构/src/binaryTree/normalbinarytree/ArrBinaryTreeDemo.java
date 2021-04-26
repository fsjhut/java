package binaryTree.normalbinarytree;
/**
 * @className: ArrBinaryTreeDemo
 * @description:   
 * @author SunHang
 * @createTime 2021/4/5 20:57
 */

/**顺序存储二叉树
 * 特点:
 * 1. 顺序存储二叉树只考虑完全二叉树
 * 2. 第n个元素的左子节点为 2*n+1
 * 3. 第n个元素的右子节点为2*n+2
 * 4. 第n个元素的父节点为(n-1)/2
 * 5. n: 表示二叉树中的第几个元素(按0开始编号的)
 * 6...二叉树的前序遍历,中序遍历
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        arrBinaryTree.infixOrder(0);
    }
}

// 编写一个类,实现顺序存储二叉树,完全二叉树
class ArrBinaryTree{
    private Integer[] arr;
    public ArrBinaryTree(Integer[] arr){
        this.arr = arr;
    }

    // 重载
    public void preOrder(){
        this.preOrder(0);
    }
    // 编写一个方法完成顺序存储二叉树的前序遍历
    // index: 数组的下标
    public void preOrder(int index){
        // 如果数组为空,或者长度为0
        if(arr == null || arr.length==0){
            System.out.println("数组为空,无法遍历");
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if(2*index+1<arr.length){
            preOrder(2*index+1);
        }
        if(2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }

    // 中序遍历
    public void infixOrder(int index){
        // 如果数组为空,或者长度为0
        if(arr == null || arr.length==0){
            System.out.println("数组为空,无法遍历");
        }
        // 向左递归遍历
        if(2*index+1<arr.length){
            preOrder(2*index+1);
        }
        // 输出当前元素
        System.out.println(arr[index]);
        if(2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }
}
