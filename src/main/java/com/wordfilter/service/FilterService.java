package com.wordfilter.service;


import com.wordfilter.pojo.TrieNode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: YZX
 * @JDK: 1.8
 * @Description: 过滤器操作
 * @CreateTime: 2024/4/11 17:08
 */
public class FilterService {

    //敏感词替换
    public String wordFilter(String path, String text) throws Exception {
        WordService wordService = new WordService();
        //依靠敏感词文本，生成敏感词树
        TrieNode sensitiveWordTree = wordService.addTextWord(path);
        //初始化一个空白的节点，用来每次判断敏感词，先指向敏感词树
        TrieNode tempNode = sensitiveWordTree;
        //先判断字符串是否为null或者空白
        if (StringUtils.isBlank(text)){
            return null;
        }
        //开始遍历，指向敏感词的开头
        int begin = 0;
        //当前位置，指向敏感词的结尾
        int position = 0;
        //存储最后的转化
        StringBuilder result = new StringBuilder();
        //遍历语句
        while (position < text.length()){
            //获取每个一字符
            char value = text.charAt(position);
            //获得当前词的子节点
            tempNode = tempNode.getChildNode(value);
            //1. 不存在联想的敏感词，直接添加
            if (tempNode == null){
                //1.1 两个指针位置不同，表明以前存在过部分敏感词，但是其本身不构成敏感词
                if (begin != position){
                    //截取添加
                    result.append(text, begin, position);
                    //移动
                    begin = position;
                }else {
                    //1.2 指针位置相同，表明就是普通添加
                    result.append(value);
                    //都移动
                    begin++;
                    position++;
                }
                //重置敏感树
                tempNode = sensitiveWordTree;
            }else {
                //2. 联想词不为空，表明可能存在敏感词
                //2.1 敏感词是结尾，替换
                if (tempNode.isEnd()){
                    result.append(repeat("*",(position-begin+1)));
                    //移动
                    position++;
                    //因为begin在敏感词的开头，所以移动到相同的位置
                    begin = position;
                    //重置敏感树
                    tempNode = sensitiveWordTree;
                }else {
                    // 2.2 当前此词不是敏感词的结尾，说明可能下面还有敏感词，position移动
                    position++;
                }
            }
        }
        //因为最后一个字可能单独构成敏感词的一部分，但是无法遍历到，所以单独添加最后一个字
        if (begin != position){
            result.append(text.charAt(text.length()-1));
        }
        return result.toString();
    }


    //重复若干次某个字符串
    public String repeat(String str, int count){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

}
