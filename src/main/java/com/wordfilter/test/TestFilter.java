package com.wordfilter.test;

import com.wordfilter.pojo.TrieNode;
import com.wordfilter.service.FilterService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @Author: YZX
 * @JDK: 1.8
 * @Description: 测试过滤
 * @CreateTime: 2024/4/16 15:30
 */
public class TestFilter {
    public static void main(String[] args) throws Exception {
        String path = "src/main/java/com/wordfilter/text/sensitiveWords.txt";
        String text = "日本天天地震，我们现在怀疑你出售答案，甚至提供考试作弊的帮助，号称都可以代开发票的，你是个笨蛋对吗，天皇，天怒，不理解";
        FilterService filterService = new FilterService();
        String turnText = filterService.wordFilter(path, text);
        System.out.println("转化前：【"+text+"】");
        System.out.println("转化前：【"+turnText+"】");
    }
}
