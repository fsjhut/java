package com.IO.java;

import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author SunHang
 * @className: FileTest
 * @description:
 * @createTime 2021/4/6 21:05
 */
public class FileTest {
    @Test
    public void test1() {
        File file = new File("E:\\One\\day17");
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.mkdirs());
    }

    /**
     * 查询指定目录下的所有的文件
     */
    public static void selectChild1(File parent, String str) {
        Objects.requireNonNull(parent);
        if (!parent.exists()) {
            return;
        }
        File[] files = parent.listFiles();
        for (File file : files) {
            System.out.println(str + file.getName());
            if (file.isDirectory()) {
//                System.out.print("\t");
                selectChild1(file, "| " + str);
            }
        }
    }

    public static void selectChild2(File parent, String str) {
        String[] lists = parent.list();
        for (String list : lists) {
            System.out.println(str + list);
            File child = new File(parent, list);
            if (child.isDirectory()) {
                selectChild2(child, "| " + str);
            }
        }
    }

    @Test
    public void test3() {
//        String parent = "E:\\One\\day17";
        File parent = new File("E:\\One\\day17");
//        selectChild1(parent, "|");
        selectChild4(parent,"|");
    }

    private static void selectChild4(File parent, String str) {
        File[] files = parent.listFiles((parent1,childName)->{
            if(new File(parent1,childName).isDirectory()) return true;
            return childName.endsWith("java");
        });
        for (File child : files) {
            System.out.println(str + child.getName());
            if (child.isDirectory()) {
                selectChild4(child, "| " + str);
            }
        }
    }



//    public static List<Node> selectNode(){
//        return list.parallelStream()
//                .filter(node -> node.getParentId().equals(0))
//                .peek(parentNode -> parentNode.setChildNodeList(selectChildNode(parentNode)))
//                .collect(Collectors.toList());
//    }
//
//    private static List<Node> selectChildNode(Node parentNode) {
//        //节点pid==父节点id
//        return list.parallelStream()
//                .filter(node -> node.getParentId().equals(parentNode.getId()))
//                .peek(child -> child.setChildNodeList(selectChildNode(child)))
//                .collect(Collectors.toList());
//    }
//    @Test
//    public void test4(){
//        selectNode();
//        selectChildNode();
//    }
}
