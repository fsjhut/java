package com.thread;

import lombok.*;

import java.util.List;

/**
 * @className: Node
 * @description:   
 * @author SunHang
 * @createTime 2021/4/24 22:56
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node {
    private Integer id;//节点id
    private String name;//节点名称
    private Integer parentId;//父级节点
    private List<Node> childNodeList;//当前节点下的子级
}
