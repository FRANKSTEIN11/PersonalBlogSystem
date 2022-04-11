package com.example.utils;

import cn.hutool.dfa.WordTree;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yenanren
 * @date 2022/4/11 0011
 * @Description
 */
public class WordBlackListUtil {

    private static volatile WordTree wordTree;

    private static ReentrantLock initLock = new ReentrantLock(false);

    public static void init() {
        getInstance();
        wordTreeInit();
    }

    public static void getInstance() {
        if (wordTree == null) {
            try {
                if (initLock.tryLock(2, TimeUnit.SECONDS)) {
                    if (wordTree == null) {
                        wordTree = new WordTree();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                initLock.unlock();
            }
        }
    }

    public static void wordTreeInit() {
        wordTree.addWord("妖");
        wordTree.addWord("魔");
        wordTree.addWord("鬼");
        wordTree.addWord("怪");
    }

    public static boolean isContains(String str) {
        return wordTree.isMatch(str);
    }

    public static void addWord(String str) {
        wordTree.addWord(str);
    }


    public static void main(String[] args) {
        String str = "作妖";
//        wordTreeInit();
        System.out.println(isContains(str));
    }

    private WordBlackListUtil() {
    }

}
