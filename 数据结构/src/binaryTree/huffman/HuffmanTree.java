package binaryTree.huffman;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author SunHang
 * @className: HuffmanTree
 * @description: 创建一个赫夫曼树的方法
 * @createTime 2021/4/11 14:37
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node1 huffmanTree = createHuffmanTree(arr);
        System.out.println("---------------");
        // 树已创建完毕，进行前序遍历 测试是否正确
        preOrder(huffmanTree);

    }

    private static void preOrder(Node1 huffmanTree) {
        if(huffmanTree == null){
            System.out.println("赫夫曼树不能是空！");
            return;
        }
        System.out.print(huffmanTree.value+"\t");
        if(huffmanTree.left!=null){
            preOrder(huffmanTree.left);
        }
        if(huffmanTree.right!=null){
            preOrder(huffmanTree.right);
        }
    }

    public static Node1 createHuffmanTree(int[] arr) {
        // 第一步：遍历arr数组
        // 第二步：将arr的每个元素构成一个node
        // 第三步：将node放入ArrayList中
        ArrayList<Node1> nodes = new ArrayList<>();
        for (int value : arr) {
            Node1 node = new Node1(value);
            nodes.add(node);
        }
        // 先排序 从小到大
        while (nodes.size() > 1) {
            Collections.sort(nodes);
//            System.out.println(nodes);
            // 取出最小的二叉树节点
            Node1 left = nodes.remove(0);

            Node1 right = nodes.remove(0);
            // 构建一个新的二叉树
            Node1 parent = new Node1(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
            System.out.println(nodes);
        }
        return nodes.get(0);
    }
}

// 为了让node支持排序，则需要实现comparable接口
class Node1 implements Comparable<Node1> {
    int value;
    Node1 left;
    Node1 right;

    public Node1(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }

    @Override
    public int compareTo(Node1 o) {
        return this.value - o.value;
    }
}
