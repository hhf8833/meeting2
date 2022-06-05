package com.hhf.classification.backtrack.bfs;

import java.util.*;

/**
 * @author HP
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 *
 * 利用dfs找到最短路径
 */
public class No_127_ladderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)){
            return 0;
        }
        boolean[] visited = new boolean[wordList.size()+1];
        Queue<String> queue = new ArrayDeque<>();

        int count =0;
        queue.offer(beginWord);
        int indexOf = wordList.indexOf(beginWord);
        if (indexOf !=-1){
            visited[indexOf] =true;
        }
        while (!queue.isEmpty()){
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String pollword = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String word = wordList.get(j);
                    if (visited[j]){
                        continue;
                    }
                    if (!canChangeToWord(pollword,word)){
                        continue;
                    }
                    if (endWord.equals(word)){
                        return count+1;
                    }
                    queue.offer(word);
                    visited[i] = true;
                }


            }
        }
        return 0;
    }

    private boolean canChangeToWord(String pollword, String word) {
        int count =0,n1=pollword.length(),n2=word.length();
        if (n1 !=n2){
            return false;
        }
        for (int i = 0; i < n1; i++) {
            if (pollword.charAt(i) != word.charAt(i)){
                count++;
                if (count>1){
                    return false;
                }
            }
        }
        return count ==1;
    }

}
