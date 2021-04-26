package binaryTree.hufuman_exec1;
/**
 * @className: Node
 * @description:   
 * @author SunHang
 * @createTime 2021/4/12 13:04
 */

/**
 * 1. Node一个节点，data：保存字符对应的Ascii码
 * 2. weight：保存该data所对应的权重
 * 3. left: 左节点
 * 4. right: 右节点
 * 5. 需要实现comparable接口，可以方便自动排序
 */
public class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node[" +
                "data=" + data +
                ", weight=" + weight +
                ']';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight-o.weight;
    }
    // 实现前序遍历查找
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
