package com.wordfilter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YZX
 * @JDK: 1.8
 * @Description: 前缀树
 * @CreateTime: 2024/4/11 15:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrieNode {
    //当前节点的值
    private char value;
    //标识是否为关键词的结尾
    private boolean end = false;
    //子节点Map(节点名称，节点)，一个节点可以有多个子节点，不同节点的子节点可以重合
    private Map<Character,TrieNode> childNodeMap = new HashMap<>();

    //关键词的结尾，注解方法中已经封装
    //currentNode.isEnd();

    //设置关键词的结尾，注解方法中已经封装
    //currentNode.setEnd();

    //添加叶子节点
    public void addChildNode(Character value, TrieNode node){
        childNodeMap.put(value,node);
    }

    //获取子节点
    public TrieNode getChildNode(Character value){
        return childNodeMap.get(value);
    }

}
