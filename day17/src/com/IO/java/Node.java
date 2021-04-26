package com.IO.java;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Node
 * @description:   
 * @author SunHang
 * @createTime 2021/4/6 21:29
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node {
    private Integer id;
    private String name;//节点名称
    private Integer parentId;//父级节点
    private List<Node> childNodeList;//当前节点下的子级节点
}
