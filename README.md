# 敏感词过滤

---
> 说明

本项目可用于敏感词过滤，并将其替换为`*`表示

- `TrieNode.java`：描述节点类，用于构造敏感词节点
- `WordService.java`：实现敏感词树的构造
- `FilterService.java`：实现敏感词的替换

> 使用
1. 下载项目
2. 将敏感词文本上传到`src/main/java/com/wordfilter/text/`下，目前只支持识别一个敏感词文本文件
3. 在`src/main/java/com/wordfilter/test/TestFilter`下可进行测试