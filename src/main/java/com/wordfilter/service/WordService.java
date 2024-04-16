package com.wordfilter.service;
import com.wordfilter.pojo.TrieNode;

import java.io.*;
import java.util.Map;

/**
 * @Author: YZX
 * @JDK: 1.8
 * @Description: 单词节点树
 * @CreateTime: 2024/4/11 16:23
 */
public class WordService {

    /**
    * @Description: 用于根据敏感词文本，生成敏感词树
    * @Param: [path 敏感词文本的路径]
    * @return: com.yzx.sentivewordfilter.pojo.TrieNode 返回一个敏感词树
    * @Author: yzx
    * @Date: 2024/4/12
    */
    public TrieNode addTextWord(String path) throws Exception {
        File file = new File(path);
        //字节流
        FileInputStream fileStream = new FileInputStream(file);
        //转化流，字节转化到字符流
        InputStreamReader inputStreamReader = new InputStreamReader(fileStream,"GBK");
        //缓冲流，增强普通字符流
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //读取的字符串
        String line = "";
        //初始化一个根节点
        TrieNode rootNode = new TrieNode();
        while ((line = reader.readLine())!=null){
            //一个词一个词插入
            addWord(rootNode,line);
        }
        return rootNode;
    }

    /**
    * @Description: 插入敏感词
    * @Param: [currentNode 初始化的根节点，没有任何数据, keyword 要插入的关键词]
    * @return: void
    * @Author: yzx
    * @Date: 2024/4/11
    */
    public void addWord(TrieNode currentNode, String keyword) throws Exception {
        //敏感词中本身不能拥有*号
        if (keyword.contains("*")){
            //抛出非法异常
            throw new Exception("敏感词【" + keyword + "】中不能拥有*号");
        }
        for (int i = 0; i < keyword.length(); i++) {
            //将中文字符变为字符
            char value = keyword.charAt(i);
            //判断是否拥有相同的子节点
            TrieNode childNode = currentNode.getChildNode(value);
            //没有相同的子节点
            if (childNode == null){
                //初始化一个新的子节点
                childNode = new TrieNode();
                childNode.setValue(value);
                //设置子节点
                currentNode.addChildNode(value,childNode);
            }
            //指向子节点，进入下一层循环
            currentNode = childNode;

            //敏感词结束
            if (i == (keyword.length() -1) ){
                currentNode.setEnd(true);
            }
        }
    }
}
