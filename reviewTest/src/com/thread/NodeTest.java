package com.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunHang
 * @className: NodeTest
 * @description:
 * @createTime 2021/4/24 22:59
 */
public class NodeTest {
    private static List<Node> list;
    static {
        list = new ArrayList<>(10);
        //1.一级资源
        list.add(new Node(1, "爷爷", 0, null));
        list.add(new Node(2, "二爷", 0, null));
        //2.二级资源
        list.add(new Node(3, "爷爷-son1", 1, null));
        list.add(new Node(4, "爷爷-son2", 1, null));
        list.add(new Node(5, "爷爷-son3", 1, null));
        list.add(new Node(6, "二爷-son1", 2, null));
        list.add(new Node(7, "二爷-son2", 2, null));
        //3. 三级资源
        list.add(new Node(8, "爷爷-son1-son1", 3,
                null));
        list.add(new Node(9, "爷爷-son1-son2", 3,
                null));
        list.add(new Node(10, "爷爷-son2-son1", 4,
                null));
        list.add(new Node(11, "二爷-son1-son1", 6,
                null));
        list.add(new Node(12, "二爷-son1-son2", 6,
                null));
    }
    private static List<Node> selectNode() {
//获得集合里面的1级资源
        return list.parallelStream()
                .filter(node ->
                        node.getParentId().equals(0))
                .peek(parentNode ->
                        parentNode.setChildNodeList(selectChildNode(parentNode)))
                .collect(Collectors.toList());
    }
    private static List<Node> selectChildNode(Node parentNode) {
//节点pid==父节点id
        return list.parallelStream()
                .filter(node ->
                        node.getParentId().equals(parentNode.getId()))
                .peek(child ->
                        child.setChildNodeList(selectChildNode(child)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        selectNode();
        System.out.println(list);
        String string = JSON.toJSONString(list);
        System.out.println(string);
    }
}

